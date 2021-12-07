package com.weishop.test.jetpack.navigation

import android.os.Bundle
import android.os.Process
import androidx.appcompat.app.AppCompatActivity
import com.weishop.test.databinding.ActivityDestinationBinding
import com.weishop.test.util.LogUtils

class DestinationActivity : AppCompatActivity() {
    private var binding: ActivityDestinationBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("DestinationActivity pid=${Process.myPid()},taskId=${taskId}")
        binding = ActivityDestinationBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}