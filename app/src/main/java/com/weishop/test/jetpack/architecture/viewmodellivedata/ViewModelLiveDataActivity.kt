package com.weishop.test.jetpack.architecture.viewmodellivedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.weishop.test.databinding.ActivityViewmodelLivedataBinding
import com.weishop.test.util.LogUtils

class ViewModelLiveDataActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityViewmodelLivedataBinding
    private val model: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("ViewModelLiveDataActivity onCreate")
        mBinding = ActivityViewmodelLivedataBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.start.setOnClickListener {

        }
        model.getName().observe(this, {
            LogUtils.d("observe $it")
        })

    }

    override fun onResume() {
        LogUtils.d("ViewModelLiveDataActivity onResume")
        super.onResume()
    }

    override fun onPause() {
        LogUtils.d("ViewModelLiveDataActivity onPause")
        super.onPause()
    }


    override fun onStart() {
        LogUtils.d("ViewModelLiveDataActivity onStart")
        super.onStart()

    }

    override fun onStop() {
        LogUtils.d("ViewModelLiveDataActivity onStop")
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("ViewModelLiveDataActivity onDestroy")
    }

}
