package com.weishop.test.kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.weishop.test.R
import com.weishop.test.util.LogUtils
import com.weishop.test.util.TestUtils
import kotlinx.coroutines.*
import java.text.DateFormat
import java.util.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class KTTestActivity : AppCompatActivity(), View.OnClickListener {
    private var testTextView: TextView? = null
    private var button1: Button? = null
    private var button2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt)
        button1 = findViewById<Button>(R.id.btn1)
        button2 = findViewById<Button>(R.id.btn2)
        testTextView = findViewById<TextView>(R.id.kttest);

        button1?.setOnClickListener(this)
        button2?.setOnClickListener(this)

//        init()

    }


    private fun init() {
        GlobalScope.launch(Dispatchers.Main) {
            LogUtils.d("launch isMainThread=${TestUtils.isMainThread()}")
            val result = withContext(Dispatchers.IO) {
                LogUtils.d("withContext IO isMainThread=${TestUtils.isMainThread()}")

                withContext(Dispatchers.Main) {
                    handleUi()
                    LogUtils.d("handleUi after=${TestUtils.isMainThread()}")
                }

            }

        }

    }


    private suspend fun handleUi() {
        LogUtils.d("handleUi =${TestUtils.isMainThread()}")
        Thread.sleep(2000)

    }

    fun log(msg: Any?) {
        LogUtils.d("[${Thread.currentThread().name}] $msg")
    }

    fun testSuspendCoroutine() {
        GlobalScope.launch(Dispatchers.Main) {
            println("isMain=${TestUtils.isMainThread()} 1")
            //执行完以后才会接着往下执行
            withContext(Dispatchers.IO) {
                test1()
            }
            println("withContext after")
        }
        println("testSuspendCoroutine after")

    }

    private fun test1() {


    }

    var continuation: Continuation<String>? = null

    val func: (continuation: Continuation<String>) -> Unit = {
        continuation = it
        println("getRemoteDataFromLocalCache isMain=${TestUtils.isMainThread()}")
        Thread.sleep(5000)
        println("getRemoteDataFromLocalCache after")

    }

    private suspend fun getRemoteDataFromLocalCache(): String = suspendCoroutine(func)

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn1) {
            testSuspendCoroutine()
        } else {
            continuation?.resume("result..")
        }

    }


    fun getDateZeroZone(time: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        // 设置为时区无关的
        calendar.timeZone = TimeZone.getTimeZone("GMT+00:00")
        // 设置0点
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        return calendar.timeInMillis
    }
}