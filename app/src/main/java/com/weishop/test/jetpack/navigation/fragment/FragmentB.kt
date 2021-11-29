package com.weishop.test.jetpack.navigation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weishop.test.R
import com.weishop.test.util.TestUtils

class FragmentB:Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TestUtils.TAG, "FragmentB onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TestUtils.TAG, "FragmentB onDetach")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TestUtils.TAG, "FragmentB onCreateView")
        return inflater.inflate(R.layout.fragment_b, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TestUtils.TAG, "FragmentB onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TestUtils.TAG, "FragmentB onDestroy")
    }




}