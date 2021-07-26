package com.weishop.test.jetpack.architecture.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weishop.test.databinding.ActivityArchitectureBinding

class ArchitectureFramgent : Fragment() {

    private var mBinding: ActivityArchitectureBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = ActivityArchitectureBinding.inflate(layoutInflater,container,false)
        return mBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //记住清除引用
        mBinding = null
    }
}