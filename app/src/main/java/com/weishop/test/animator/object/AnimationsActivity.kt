package com.weishop.test.animator.`object`

import android.animation.*
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.weishop.test.R
import com.weishop.test.util.LogUtils

class AnimationsActivity : AppCompatActivity() {
    private lateinit var animateView: AppCompatTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animations)
        animateView = findViewById<AppCompatTextView>(R.id.animate_view)
        findViewById<Button>(R.id.testbtn).setOnClickListener {
            testObjectAnimatonFromXml()
        }
        animateView.setOnClickListener {
            LogUtils.d("移动 click")
        }

    }

    /**
     * 测试补间动画,从xml加载
     */
    fun testTweenAnimationFromXml(){
        val loadAnimation :Animation = AnimationUtils.loadAnimation(this, R.anim.translate_view)
        animateView.startAnimation(loadAnimation)


    }

    /**
     * 测试属性动画，从xml加载
     */
    fun testObjectAnimatonFromXml(){
        (AnimatorInflater.loadAnimator(this,R.animator.translate_object_view) as AnimatorSet).apply {
            setTarget(animateView)
            start()
        }

    }


    fun testObjectAnimatorSet() {
        val animatorSet = AnimatorSet()

    }

    fun testObjectAnimator() {
        ObjectAnimator.ofFloat(animateView, "translationX", 100f, 50f, 50f).apply {
            duration = 1000
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                }
            })
            start()
        }

    }


    fun testValueAnimator() {
        ValueAnimator.ofFloat(0f, 100f).apply {
            duration = 500
            start()
            addUpdateListener { animation ->
                LogUtils.d("animatedFraction=${animation.animatedFraction},animatedValue=${animation.animatedValue}")
            }
        }


    }

    /**
     * 在一秒内从0到100 ，然后再从100到200，animatedValue的值就是evaluate函数返回的值
     */
    fun testValueAnimator2() {
        ValueAnimator.ofObject(object : TypeEvaluator<Int> {
            override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
                return startValue + endValue
            }
        }, 0, 100, 200).apply {
            duration = 1000
            start()
            addUpdateListener { animation ->
                LogUtils.d("animatedFraction=${animation.animatedFraction},animatedValue=${animation.animatedValue}")
            }
        }

    }

}