package com.weishop.test.jetpack.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weishop.test.databinding.ActivityNavigationDemoBinding

class NavigationDemo : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //NavHostFragment 是所有导航内所有framgent的parentfragment
        binding = ActivityNavigationDemoBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

    }
}