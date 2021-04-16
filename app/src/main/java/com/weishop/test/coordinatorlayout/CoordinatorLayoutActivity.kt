package com.weishop.test.coordinatorlayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.weishop.test.R
import com.weishop.test.util.LogUtils

class CoordinatorLayoutActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var appBarLayout: AppBarLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinatorlayout_collapsingtoolbarlayout)
        appBarLayout = findViewById(R.id.appbar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener
        { appBarLayout, verticalOffset ->
            LogUtils.d("verticalOffset=${verticalOffset},totalScrollRange=${appBarLayout?.totalScrollRange}")
        })

    }

    override fun onClick(v: View) {}

    override fun onDestroy() {
        super.onDestroy()
    }
}