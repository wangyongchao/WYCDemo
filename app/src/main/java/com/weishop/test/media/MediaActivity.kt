package com.weishop.test.media

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weishop.test.R
import com.weishop.test.util.AppUtils
import com.weishop.test.util.LogUtils
import com.weishop.test.util.TestUtils
import kotlinx.android.synthetic.main.activity_media.*
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.schedule

class MediaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)
        main_test.setOnClickListener {
            testCroutine()


        }
        thread_test.setOnClickListener {}


    }

    fun testCroutine() {
        GlobalScope.launch(Dispatchers.Main) {
            val targetFinished = targetFinished()
            println("launch ${Thread.currentThread().name},${targetFinished},${AppUtils.isMainThread()}")
        }
        println("testCroutine ${Thread.currentThread().name}")
    }

    private suspend fun targetFinished():String = withContext(Dispatchers.Main) {
        println("targetFinished ${Thread.currentThread().name},${AppUtils.isMainThread()}")
        thread_test.setText("2")
        delay(60*1000)
        runOnUiThread {
            thread_test.setText("3")
        }
        println("targetFinished after ${Thread.currentThread().name}")
        return@withContext "3"
    }


    fun testBlock() {
        Timer().schedule(3000) {
            LogUtils.d("schedule ${Thread.currentThread().name},ismain=${TestUtils.isMainThread()}")
//            while (true) {
//                LogUtils.d("t2:${Thread.currentThread().state}")
//                testLaunch()
//            }
        }
    }


    fun testLaunch() {

        GlobalScope.launch(Dispatchers.Main) {
            try {
                LogUtils.d("launch ${Thread.currentThread().name},ismain=${TestUtils.isMainThread()}")
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}