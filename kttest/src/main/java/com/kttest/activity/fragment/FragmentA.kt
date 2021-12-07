package com.kttest.activity.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.kttest.R
import com.kttest.activity.TAG
import com.kttest.databinding.FragmentABinding

class FragmentA : Fragment() {

    private var binding: FragmentABinding? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "FragmentA otherother onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "FragmentA otheronDetach")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "FragmentA otheronCreateView")
        binding = FragmentABinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "FragmentA otheronViewCreated")
        binding?.btn?.setOnClickListener {
//            val actionToB = FragmentADirections.actionAToB()
//            findNavController().navigate(actionToB)
//            binding?.root?.findNavController()?.navigate(R.id.action_to_b)
            //id必须设置有navigationcontroler
//            activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.action_to_b)
//            findNavController().navigate(R.id.action_to_b,null, navOptions {
//                anim {
//                    enter=R.anim.slide_in_right
//                    exit=R.anim.slide_in_left
//                }
//            })
            view.findNavController().navigate(R.id.action_a_to_b)


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "FragmentA otheronCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        Log.d(TAG, "FragmentA otheronDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "FragmentA otheronResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "FragmentA otheronPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "FragmentA otheronDestroyView")
    }

}