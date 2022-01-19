package com.weishop.test.jetpack.architecture.viewmodellivedata

import android.content.Intent
import android.os.*
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.util.Function
import androidx.lifecycle.*
import com.weishop.test.databinding.ActivityViewmodelLivedataBinding
import com.weishop.test.databinding.DialogCornerBinding
import com.weishop.test.util.LogUtils
import com.weishop.test.util.RomUtil
import com.weishop.test.util.StatusBarUtils

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

            val prop = RomUtil.getProp("")
            LogUtils.d("${android.os.Build.BRAND},${android.os.Build.MODEL}")

        }

        mBinding.start.setOnClickListener {
            mutableLiveData.value = "set"
        }


        mutableLiveData.observe(this) {
            LogUtils.d("observe $it")

        }
        StatusBarUtils.setTransparentBar(this, true)
        var statusBarHeight = 0
        if (Build.VERSION.SDK_INT >= 21) {
            statusBarHeight = StatusBarUtils.getStatusBarHeight(this)
        }
        mBinding.container.setPadding(0, statusBarHeight, 0, 0)


    }

    fun test() {
        val resetAppIntent = Intent(this, LiveDataActivity::class.java)
        resetAppIntent.putExtra("isNewUser", true)
        startActivity(resetAppIntent)
        Process.killProcess(Process.myPid())
        System.exit(0)
    }

    fun showDialog() {
        val dialog = AlertDialog.Builder(this).create()
        val binding = DialogCornerBinding.inflate(layoutInflater)
        dialog.setView(binding.root)
        dialog.window?.setDimAmount(0f)
        dialog.window?.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
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

        liveDataMerger.observe(this, Observer {
            LogUtils.d("liveDataMerger $it")
        })

        mutableLiveData1.postValue("1111")

    }

    fun testChangeData() {
        val map: LiveData<Int> = Transformations.map(mutableLiveData) {
            LogUtils.d("observe $it")
            //返回一个具体的改变后的数值
            33
        }

        map.observe(this, Observer {

        })

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
