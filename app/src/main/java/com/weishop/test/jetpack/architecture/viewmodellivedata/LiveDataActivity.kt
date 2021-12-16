package com.weishop.test.jetpack.architecture.viewmodellivedata

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
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
            mutableLiveData.postValue("post")

        }

        mBinding.start.setOnClickListener {
            mutableLiveData.value = "set"
        }


        mutableLiveData.observe(this) {
            LogUtils.d("observe $it")

        }

        val map = Transformations.map(mutableLiveData) {
            LogUtils.d("observe $it")
            33
        }
        map.observe(this) {

        }

        var transformSwitchMap: LiveData<String> =
            Transformations.switchMap(mutableLiveData, Function {
                LogUtils.d("switchMap $it")
                MutableLiveData<String>(it + "transform")
            })

        transformSwitchMap.observe(this, Observer {
            LogUtils.d("transform observe  $it")
        })


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
