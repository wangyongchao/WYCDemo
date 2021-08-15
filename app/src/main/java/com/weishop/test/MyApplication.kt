package com.weishop.test

import android.app.Application
import android.content.Context
import androidx.startup.AppInitializer
import com.github.moduth.blockcanary.BlockCanary
import com.squareup.leakcanary.LeakCanary
import com.weishop.test.jetpack.architecture.initializer.ArouteInitializer
import com.weishop.test.performance.AppBlockCanaryContext
import com.weishop.test.util.CrashHandler
import com.weishop.test.util.LogUtils
import com.weishop.test.backgroudtask.ScreenBroadcastReceiver
import android.content.Intent

import android.content.IntentFilter


/**
 * Created by wangyongchao on 16/7/1.
 */
class MyApplication : Application() {
    private val clientId = "dsfa323234342"
    private var mScreenBroadcastReceiver: ScreenBroadcastReceiver? = null
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        //        PluginManager.getInstance(base).init();
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //manifest中禁用自动初始化，改为手动初始化
        AppInitializer.getInstance(this).initializeComponent(ArouteInitializer::class.java)

//        if (null == mScreenBroadcastReceiver)
//            mScreenBroadcastReceiver = ScreenBroadcastReceiver()
//        val intentFilter = IntentFilter()
//        intentFilter.addAction(Intent.ACTION_SCREEN_ON) //解锁
//        intentFilter.addAction(Intent.ACTION_SCREEN_OFF) //锁屏
//        intentFilter.addAction(Intent.ACTION_USER_PRESENT) //开屏
//        registerReceiver(mScreenBroadcastReceiver, intentFilter)

    }

    private fun intLeakCanary() {
        LeakCanary.install(this)
        CrashHandler.getInstance().init(this)
        BlockCanary.install(this, AppBlockCanaryContext()).start()
    }


    private fun initLog() {
//        LoganConfig config = new LoganConfig.Builder()
//                .setCachePath(getApplicationContext().getFilesDir().getAbsolutePath())
//                .setPath(getApplicationContext().getExternalFilesDir(null).getAbsolutePath()
//                        + File.separator + "logan_v1")
//                .setEncryptKey16("0123456789012345".getBytes())
//                .setEncryptIV16("0123456789012345".getBytes())
//                .build();
//        Logan.init(config);
    }

    override fun onLowMemory() {
        super.onLowMemory()
        LogUtils.d("MyApplication onLowMemory")
    }


    companion object {
        @JvmStatic
        var instance: MyApplication? = null
            private set
        private val customTagPrefix: String? = "custom"
        fun generateTag(stack: StackTraceElement): String {
            var tag = "%s.%s(L:%d)"
            var className = stack.className
            className = className.substring(className.lastIndexOf(".") + 1)
            tag = String.format(tag, stack.className, className, stack.lineNumber)
            tag = if (customTagPrefix == null) tag else customTagPrefix + ":" + tag
            return tag
        }
    }
}