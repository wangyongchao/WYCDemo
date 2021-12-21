package com.weishop.test.jetpack.architecture.viewmodellivedata

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.util.Function
import androidx.lifecycle.*
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
            testMediatorLiveData()

        }

        mBinding.start.setOnClickListener {
            mutableLiveData.value = "set"
        }


        mutableLiveData.observe(this) {
            LogUtils.d("observe $it")

        }



    }

    fun testMediatorLiveData() {
        val mutableLiveData1 = MutableLiveData<String>()
        val mutableLiveData2 = MutableLiveData<String>()
        val liveDataMerger = MediatorLiveData<String>()
        liveDataMerger.addSource(mutableLiveData1) {
            LogUtils.d("mutableLiveData1 $it")

        }
        liveDataMerger.addSource(mutableLiveData2) {
            LogUtils.d("mutableLiveData2 $it")
        }

        liveDataMerger.observe(this) {
            LogUtils.d("liveDataMerger $it")
        }

        mutableLiveData1.postValue("1111")

    }

    fun testChangeData() {
        val map: LiveData<Int> = Transformations.map(mutableLiveData) {
            LogUtils.d("observe $it")
            //返回一个具体的改变后的数值
            33
        }
        map.observe(this) {

        }

        var transformSwitchMap: LiveData<String> = Transformations.switchMap(mutableLiveData, Function {
            LogUtils.d("switchMap $it")
            //必须返回livedata
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
