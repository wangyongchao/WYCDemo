package com.weishop.test.kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.weishop.test.R
import com.weishop.test.util.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*

class KTTestActivity : AppCompatActivity(), View.OnClickListener {
    private var testTextView: TextView? = null
    private var button1: Button? = null
    private var button2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt)
        button1 = findViewById<Button>(R.id.btn1)
        button2 = findViewById<Button>(R.id.btn2)
        testTextView=findViewById<TextView>(R.id.kttest);

        button1?.setOnClickListener(this)
        button2?.setOnClickListener(this)

    }


    private fun init() {
    }

    fun log(msg: Any?) {
        LogUtils.d("[${Thread.currentThread().name}] $msg")
    }

    override fun onClick(v: View?) {
        GlobalScope.launch(context = Dispatchers.Main) {
            log("launch")
            testTextView?.text="fadsfds"
        }
        LogUtils.d("after")
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