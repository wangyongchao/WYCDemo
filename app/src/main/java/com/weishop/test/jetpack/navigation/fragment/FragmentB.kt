package com.weishop.test.jetpack.navigation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.weishop.test.R
import com.weishop.test.databinding.FragmentABinding
import com.weishop.test.databinding.FragmentBBinding
import com.weishop.test.util.TestUtils

class FragmentB : Fragment() {

    private var binding: FragmentBBinding? = null

    val args: FragmentBArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TestUtils.TAG, "FragmentB onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TestUtils.TAG, "FragmentB onDetach")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TestUtils.TAG, "FragmentB onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TestUtils.TAG, "FragmentB onPause")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TestUtils.TAG, "FragmentB onCreateView")
        binding = FragmentBBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TestUtils.TAG, "FragmentB onViewCreated")
        binding?.btn?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_b_to_c)
//            val navController = Navigation.findNavController(view)
//            navController.navigateUp()

        }
        binding?.btnInnerActivity?.setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_b_to_activity)
            val savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
            savedStateHandle.set(FragmentA.LOGIN_SUCCESSFUL, true)
        }

        binding?.btnOuterActivity?.setOnClickListener {

            Navigation.findNavController(view).navigate(
                R.id.action_b_to_outer_activity,
                bundleOf("navigationId" to R.id.navigation_a)
            )
        }


        val from1other = arguments?.getString("from1")    //此种方式FragmentB不用定义argument
        val from1 = args.from1    //此种方式FragmentB需要定义argument
        Log.d(TestUtils.TAG, "from1other=${from1other}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TestUtils.TAG, "FragmentB onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        Log.d(TestUtils.TAG, "FragmentB onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TestUtils.TAG, "FragmentB onDestroyView")
    }

}