package com.weishop.test.jetpack.navigation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.weishop.test.R
import com.weishop.test.databinding.FragmentABinding
import com.weishop.test.util.LogUtils
import com.weishop.test.util.TestUtils

class FragmentA : Fragment() {
    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    private var binding: FragmentABinding? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TestUtils.TAG, "FragmentA onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TestUtils.TAG, "FragmentA onDetach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TestUtils.TAG, "FragmentA onCreateView")
        binding = FragmentABinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TestUtils.TAG, "FragmentA onViewCreated")
        binding?.btn?.setOnClickListener {
            val actionToB = FragmentADirections.actionAToB("来自a",3)
            findNavController().navigate(actionToB)
//            binding?.root?.findNavController()?.navigate(R.id.action_to_b)
            //id必须设置有navigationcontroler
//            activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.action_to_b)
//            findNavController().navigate(R.id.action_to_b,null, navOptions {
//                anim {
//                    enter=R.anim.slide_in_right
//                    exit=R.anim.slide_in_left
//                }
//            })
//            view.findNavController().navigate(R.id.action_a_to_b)
        }

        binding?.btnPop?.setOnClickListener {

        }

        registerObserver()

    }

    private fun registerObserver() {
        val navController = findNavController()
        val currentBackStackEntry = navController.currentBackStackEntry!!
        currentBackStackEntry.savedStateHandle.getLiveData<Boolean>(LOGIN_SUCCESSFUL)
            .observe(currentBackStackEntry) {
                LogUtils.d("registerObserver $it")

            }
    }

    private fun printCurrent() {
        val currentDestination = view?.findNavController()?.currentDestination
        LogUtils.d("currentDestination=$currentDestination")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TestUtils.TAG, "FragmentA onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        Log.d(TestUtils.TAG, "FragmentA onDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TestUtils.TAG, "FragmentA onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TestUtils.TAG, "FragmentA onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TestUtils.TAG, "FragmentA onDestroyView")
    }

}