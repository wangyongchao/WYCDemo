package com.weishop.test.jetpack.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.weishop.test.R
import com.weishop.test.util.LogUtils

class HomeFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.d("HomeFragment onAttach $this")
    }

    override fun onDetach() {
        super.onDetach()
        LogUtils.d("HomeFragment onDetach $this")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("HomeFragment onCreate $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("HomeFragment onDestroy $this")
    }


    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }


}