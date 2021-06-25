package com.weishop.test.coordinatorlayout

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.weishop.test.R
import com.weishop.test.util.AppUtils
import com.weishop.test.util.LogUtils
import com.weishop.test.util.TestUtils
import kotlinx.android.synthetic.main.activity_type.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoordinatorLayoutActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var appBarLayout: AppBarLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinatorlayout_appbarlayout_fullscreen)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        LogUtils.d(supportActionBar.toString());
        appBarLayout = findViewById(R.id.appbar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener
        { appBarLayout, verticalOffset ->
            LogUtils.d("verticalOffset=${verticalOffset},totalScrollRange=${appBarLayout?.totalScrollRange},${appBarLayout.height}")
        })

        appBarLayout.postDelayed(object : Runnable {
            override fun run() {
                TestUtils.getProperty(this@CoordinatorLayoutActivity)
            }

        }, 200)

        init()

    }

   fun init() {

       GlobalScope.launch(Dispatchers.Default){
           LogUtils.d("AndroidActivity"+"协程间是如何切换的")
           LogUtils.d("AndroidActivity---${Thread.currentThread().name}")
           launch {
               delay(1000)
               LogUtils.d("AndroidActivity"+"now")
           }
           LogUtils.d("AndroidActivity"+"next")
       }


       LogUtils.d("world init ${TestUtils.isMainThread()}")
    }



    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}