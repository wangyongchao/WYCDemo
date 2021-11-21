package com.weishop.test.jetpack.architecture.lifecycle

import android.app.Activity
import android.content.Context
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.weishop.test.databinding.ActivityArchitectureBinding
import com.weishop.test.util.LogUtils

class CustomLifeCycleActivity : Activity(),LifecycleOwner {
    private lateinit var mBinding: ActivityArchitectureBinding
    private lateinit var listener: MyLocationListener
    private lateinit var registry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityArchitectureBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        //自定义LifecycleOwner
        registry= LifecycleRegistry(this)
        registry.currentState=Lifecycle.State.CREATED

        listener=MyLocationListener(this,lifecycle){

        }
        lifecycle.addObserver(listener)

        mBinding.testBtn.setOnClickListener {


        }

        mBinding.testBtn2.setOnClickListener {


        }



    }

    override fun onStart() {
        registry.currentState=Lifecycle.State.STARTED
        super.onStart()
        LogUtils.d("LifeCycleActivity onStart")
    }

    override fun onStop() {
        registry.currentState=Lifecycle.State.DESTROYED
        super.onStop()
        LogUtils.d("LifeCycleActivity onStop")
    }

    override fun getLifecycle(): Lifecycle {
        return registry
    }

}



