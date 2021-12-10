package com.weishop.test.jetpack.navigation


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.weishop.test.R
import com.weishop.test.databinding.FragmentMydialogBinding
import com.weishop.test.util.TestUtils

class MyDialogFragment : BottomSheetDialogFragment() {
    var binding: FragmentMydialogBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TestUtils.TAG, "MyDialogFragment onCreate")
        setStyle(STYLE_NORMAL, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TestUtils.TAG, "MyDialogFragment onDestroy")
        binding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TestUtils.TAG, "MyDialogFragment onCreateView")
        binding = FragmentMydialogBinding.inflate(inflater)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TestUtils.TAG, "MyDialogFragment onDestroyView")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.fragmentText?.setOnClickListener {
            findNavController().navigate(R.id.dialog_to_a)

        }
    }


    override fun onStart() {
        super.onStart()
        //禁止向下滑动消失
        val parent = view?.parent
        if (parent != null && parent is View) {
            val bottomSheetBehavior = BottomSheetBehavior.from(parent as View)
            val callback: BottomSheetCallback = object : BottomSheetCallback() {
                override fun onStateChanged(view: View, i: Int) {
                    if (i == BottomSheetBehavior.STATE_DRAGGING) {
                        //判断为向下拖动行为时，则强制设定状态为展开
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(view: View, v: Float) {}
            }
            bottomSheetBehavior.setBottomSheetCallback(callback)
        }
    }


    companion object {
        const val TAG = "MyDialogFragment"

    }


}