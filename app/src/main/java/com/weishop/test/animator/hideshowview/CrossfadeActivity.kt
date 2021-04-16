package com.weishop.test.animator.`hideshowview`

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.weishop.test.R

class CrossfadeActivity : AppCompatActivity() {

    private lateinit var mContentView: View
    private lateinit var loadingView: View
    private var shortAnimationDuration: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crossfade)

        mContentView = findViewById(R.id.content)
        loadingView = findViewById(R.id.loading_spinner)

        // Initially hide the content view.
        mContentView.visibility = View.GONE

        // Retrieve and cache the system's default "short" animation time.
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        mContentView.postDelayed(object :Runnable{
            override fun run() {
                crossFade()
            }

        },2000)

    }


    override fun onStart() {
        super.onStart()
    }


    /**
     * 淡入淡出效果
     */
    fun crossFade() {
        mContentView.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration.toLong())
                    .setListener(null)
        }

        loadingView.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(object :AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                       loadingView.visibility=View.GONE
                    }
                })
    }
}
