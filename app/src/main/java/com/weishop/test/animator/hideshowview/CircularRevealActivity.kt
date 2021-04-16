package com.weishop.test.animator.hideshowview

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.weishop.test.R

/**
 * 揭露动画
 */
class CircularRevealActivity : AppCompatActivity() {
    private lateinit var mImageView: AppCompatImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circularreveal)
        findViewById<View>(R.id.display).setOnClickListener {
            display()
        }
        findViewById<View>(R.id.hide).setOnClickListener {
            hide()
        }
        mImageView = findViewById<View>(R.id.imageview) as AppCompatImageView

    }

    fun display() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // get the center for the clipping circle
            val cx = mImageView.width / 2
            val cy = mImageView.height / 2

            // get the final radius for the clipping circle
            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

            // create the animator for this view (the start radius is zero)
            val anim = ViewAnimationUtils.createCircularReveal(mImageView, cx, cy, 0f, finalRadius)
            // make the view visible and start the animation
            mImageView.visibility = View.VISIBLE
            anim.start()
        } else {
            // set the view to invisible without a circular reveal animation below Lollipop
            mImageView.visibility = View.INVISIBLE
        }
    }

    fun hide() {

    }

}
