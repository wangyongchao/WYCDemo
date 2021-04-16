package com.weishop.test.coordinatorlayout.behavior

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.weishop.test.R

class CustomBehaviorActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_behavior)
    }

    override fun onClick(v: View) {}

    override fun onDestroy() {
        super.onDestroy()
    }
}