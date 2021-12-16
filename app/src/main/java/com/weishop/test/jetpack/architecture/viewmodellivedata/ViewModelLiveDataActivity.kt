package com.weishop.test.jetpack.architecture.viewmodellivedata

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.weishop.test.databinding.ActivityViewmodelLivedataBinding
import com.weishop.test.userinterface.AppBarChildActivity
import com.weishop.test.util.LogUtils

class ViewModelLiveDataActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityViewmodelLivedataBinding
    private lateinit var viewModel: NameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("ViewModelLiveDataActivity onCreate")
        mBinding = ActivityViewmodelLivedataBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        viewModel = ViewModelProvider(this).get(NameViewModel::class.java)

        //注册完成后，会立即指向性obsever回调
//        viewModel.acquirePostName().observe(this) {
//            mBinding.displayContent.text = "$it dd"
//        }

        viewModel.saveStateLiveData.observe(this) {
            LogUtils.d("saveStateLiveData observer $it")
        }

        mBinding.changeData.setOnClickListener {
            viewModel.changeData()
        }

        mBinding.start.setOnClickListener {
            startActivity(Intent(this, AppBarChildActivity::class.java))
        }

        viewModel.switchMapLiveData.observe(this) {
            LogUtils.d("switchMapLiveData observer $it")
        }


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
