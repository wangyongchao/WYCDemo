package com.weishop.test.jetpack.architecture.initializer

import android.content.Context
import androidx.startup.Initializer
import com.weishop.test.MyApplication
import com.weishop.test.util.LogUtils
import xcrash.ICrashCallback
import xcrash.TombstoneParser
import xcrash.XCrash
import java.io.File

class XcrashInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        initXCrash()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {

        return mutableListOf(WorkManagerInitializer::class.java)
    }

    private fun initXCrash() {
        val callback = ICrashCallback { logPath, emergency ->
            try {
                TombstoneParser.parse(logPath, emergency)
                LogUtils.d("logPath=$logPath,emergency=$emergency")
                val file = File(logPath)
                if (file.exists()) {
                    val l = file.lastModified()
                    LogUtils.d("l=" + l + ",current=" + System.currentTimeMillis())
                }
                val allStackTraces = Thread.getAllStackTraces()
                val values: Collection<Array<StackTraceElement>> = allStackTraces.values
                val iterator = values.iterator()
                while (iterator.hasNext()) {
                    val stringBuilder = StringBuilder()
                    val elements = iterator.next()
                    for (i in elements.indices) {
                        val s = MyApplication.generateTag(elements[i])
                        stringBuilder.append(
                            """$s""".trimIndent()
                        )
                    }
                    LogUtils.d("s=$stringBuilder")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        XCrash.init(
            MyApplication.instance, XCrash.InitParameters()
                .setAppVersion("test1.0")
                .setJavaRethrow(true)
                .setJavaLogCountMax(10)
                .setJavaDumpAllThreadsWhiteList(
                    arrayOf(
                        "^main$", "^Binder:.*", ".*Finalizer" +
                                ".*"
                    )
                )
                .setJavaDumpAllThreadsCountMax(10)
                .setJavaCallback(callback)
                .setNativeRethrow(true)
                .setNativeLogCountMax(10)
                .setNativeDumpAllThreadsWhiteList(
                    arrayOf(
                        "^xcrash\\.sample$", "^Signal " +
                                "Catcher$", "^Jit thread pool$", ".*(R|r)ender.*", ".*Chrome.*"
                    )
                )
                .setNativeDumpAllThreadsCountMax(10)
                .setNativeCallback(callback)
                .setAnrRethrow(true)
                .setAnrLogCountMax(10)
                .setAnrCallback(callback)
                .setPlaceholderCountMax(3)
                .setPlaceholderSizeKb(512)
                .setLogFileMaintainDelayMs(1000)
        )
    }

}