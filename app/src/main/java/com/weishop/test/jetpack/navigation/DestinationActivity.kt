package com.weishop.test.jetpack.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weishop.test.databinding.ActivityDestinationBinding

class DestinationActivity : AppCompatActivity() {
    private var binding: ActivityDestinationBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinationBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}