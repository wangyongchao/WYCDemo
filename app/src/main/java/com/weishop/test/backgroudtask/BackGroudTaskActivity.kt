package com.weishop.test.backgroudtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weishop.test.databinding.ActivityBackgroudtaskBinding

class BackGroudTaskActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityBackgroudtaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBackgroudtaskBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


    }
}