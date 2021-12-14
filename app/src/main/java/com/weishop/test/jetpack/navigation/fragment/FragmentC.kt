package com.weishop.test.jetpack.navigation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.weishop.test.MainNavigationDirections
import com.weishop.test.R
import com.weishop.test.databinding.FragmentBBinding
import com.weishop.test.databinding.FragmentCBinding
import com.weishop.test.util.TestUtils

class FragmentC : Fragment() {

    private var binding: FragmentCBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TestUtils.TAG, "FragmentC onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TestUtils.TAG, "FragmentC onDetach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TestUtils.TAG, "FragmentC onCreateView")
        binding = FragmentCBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TestUtils.TAG, "FragmentC onViewCreated")
        binding?.btn?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_c_to_a)

        }
        binding?.btnPop?.setOnClickListener {
            val actionGlobalFragmentA = MainNavigationDirections.actionGlobalFragmentA()
            Navigation.findNavController(view).navigate(actionGlobalFragmentA)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TestUtils.TAG, "FragmentC onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TestUtils.TAG, "FragmentC onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TestUtils.TAG, "FragmentC onPause")
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
        Log.d(TestUtils.TAG, "FragmentC onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TestUtils.TAG, "FragmentC onDestroyView")
    }

}