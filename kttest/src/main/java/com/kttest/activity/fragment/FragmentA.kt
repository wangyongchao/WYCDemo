package com.kttest.activity.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.kttest.R
import com.kttest.activity.TAG
import com.kttest.databinding.FragmentABinding

class FragmentA : Fragment() {

    private var binding: FragmentABinding? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "FragmentA other onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "FragmentA other onDetach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "FragmentA other onCreateView")
        binding = FragmentABinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = findNavController().graph.arguments
        val navArgument = arguments["navigationId"]
        Log.d(TAG, "FragmentA other onViewCreated ${navArgument?.defaultValue}")
        binding?.btn?.setOnClickListener {
            val navigateUp = view.findNavController().navigateUp()
            Log.d(TAG, "FragmentA other navigateUp $navigateUp")

        }
        binding?.btnPop?.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "FragmentA other onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        Log.d(TAG, "FragmentA other onDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "FragmentA other onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "FragmentA other onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "FragmentA other onDestroyView")
    }

}