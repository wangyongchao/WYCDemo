package com.weishop.test.jetpack.architecture.lifecycle

import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.OnLifecycleEvent
import com.weishop.test.databinding.ActivityArchitectureBinding
import com.weishop.test.userinterface.AppBarChildActivity
import com.weishop.test.util.LogUtils

class LifeCycleActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityArchitectureBinding
    private lateinit var listener: MyLocationListener
    private lateinit var registry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtils.d("LifeCycleActivity onCreate")
        super.onCreate(savedInstanceState)
        mBinding = ActivityArchitectureBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        listener = MyLocationListener(this, lifecycle) {

        }
        lifecycle.addObserver(listener)

        mBinding.testBtn.setOnClickListener {
            startActivity(Intent(this,AppBarChildActivity::class.java))


        }

        mBinding.testBtn2.setOnClickListener {


        }


    }

    override fun onStart() {
        LogUtils.d("LifeCycleActivity onStart")
        super.onStart()

    }

    override fun onStop() {
        LogUtils.d("LifeCycleActivity onStop")
        super.onStop()

    }

}

internal class MyLocationListener(
    private val context: Context,
    private val lifecycle: Lifecycle,
    private var callback: (Location) -> Unit
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        // connect to system location service
        LogUtils.d("MyLocationListener start")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        // disconnect from system location service
        LogUtils.d("MyLocationListener stop")
    }


}