package com.kttest.activity

import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kttest.R

import kotlinx.android.synthetic.main.activity_kt.*

const val TAG = "wangyongchao"

class OtherDestinationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_dest)
        val navigationId = intent.getIntExtra("navigationId", -1)
        Log.d(TAG, "OtherDestinationActivity pid=${Process.myPid()},taskId=${taskId},navigationId=${navigationId}")
        val navController = findNavController(R.id.nav_host_fragment)
        //传递参数到第一个fragment
        val argument = NavArgument.Builder().setDefaultValue(navigationId).build()
        navController.graph.addArgument("navigationId", argument)


    }

}
