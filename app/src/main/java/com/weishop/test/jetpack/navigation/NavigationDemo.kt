package com.weishop.test.jetpack.navigation

import android.os.Bundle
import android.os.Process
import androidx.appcompat.app.AppCompatActivity
import com.weishop.test.databinding.ActivityNavigationDemoBinding
import com.weishop.test.util.LogUtils

class NavigationDemo : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("NavigationDemo pid=${Process.myPid()},taskId=${taskId}")

        //NavHostFragment 是所有导航内所有framgent的parentfragment
        binding = ActivityNavigationDemoBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

    }
}