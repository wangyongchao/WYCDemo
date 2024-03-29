package com.weishop.test.jetpack.architecture

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.Transformations.map
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.weishop.test.R
import com.weishop.test.databinding.ActivityArchitectureBinding
import com.weishop.test.jetpack.architecture.datastore.EXAMPLE_COUNTER
import com.weishop.test.jetpack.architecture.datastore.dataStore
import com.weishop.test.util.LogUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class ArchitectureActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityArchitectureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityArchitectureBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.testBtn.setOnClickListener {
            val flow = this.dataStore.data.map { preferences ->
                preferences[EXAMPLE_COUNTER] ?: 0
            }

            GlobalScope.launch {
                flow.collect { value ->
                    LogUtils.d("value=${value}")
                }
            }

        }

        mBinding.testBtn2.setOnClickListener {

            GlobalScope.launch {

                this@ArchitectureActivity.dataStore.edit { settings ->
                    val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
                    settings[EXAMPLE_COUNTER] = currentCounterValue + 1
                }
            }


        }


    }
}