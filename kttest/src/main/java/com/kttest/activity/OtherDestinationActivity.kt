package com.kttest.activity

import android.os.Bundle
import android.os.Process
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.kttest.R

import kotlinx.android.synthetic.main.activity_kt.*

class OtherDestinationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_dest)
        Log.d("wangyongchao", "OtherDestinationActivity pid=${Process.myPid()},taskId=${taskId}")

    }

}
