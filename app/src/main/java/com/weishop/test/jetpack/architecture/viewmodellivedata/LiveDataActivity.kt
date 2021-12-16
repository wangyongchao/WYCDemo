package com.weishop.test.jetpack.architecture.viewmodellivedata

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.weishop.test.databinding.ActivityViewmodelLivedataBinding
import com.weishop.test.util.LogUtils

class LiveDataActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityViewmodelLivedataBinding
    private var mutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var handler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("LiveDataActivity onCreate")
        mBinding = ActivityViewmodelLivedataBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.changeData.setOnClickListener {
            handler.postDelayed(Runnable {
                mutableLiveData.postValue("post")

            },5000)


        }

        mBinding.start.setOnClickListener {
            mutableLiveData.value="set"

        }

        mutableLiveData.observe(this) {
            LogUtils.d("observe $it")

        }


    }

    override fun onResume() {
        LogUtils.d("LiveDataActivity onResume")
        super.onResume()
    }

    override fun onPause() {
        LogUtils.d("LiveDataActivity onPause")
        super.onPause()
    }


    override fun onStart() {
        LogUtils.d("LiveDataActivity onStart")
        super.onStart()

    }

    override fun onStop() {
        LogUtils.d("LiveDataActivity onStop")
        super.onStop()

    }

    override fun onDestroy() {
        LogUtils.d("LiveDataActivity onDestroy")
        super.onDestroy()
    }

}
