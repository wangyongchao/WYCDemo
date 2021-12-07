package com.kttest.activity.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.kttest.R
import com.kttest.activity.TAG
import com.kttest.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private var binding: FragmentBBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "FragmentB OtheronAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "FragmentB OtheronDetach")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "FragmentB OtheronResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "FragmentB OtheronPause")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "FragmentB OtheronCreateView")
        binding = FragmentBBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "FragmentB OtheronViewCreated")
        binding?.btn?.setOnClickListener {
//            val navController = Navigation.findNavController(view)
//            navController.navigateUp()

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "FragmentB OtheronCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        Log.d(TAG, "FragmentB OtheronDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "FragmentB OtheronDestroyView")
    }

}