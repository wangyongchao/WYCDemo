package com.weishop.test.jetpack.architecture.initializer

import android.content.Context
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager
import com.weishop.test.util.LogUtils

class WorkManagerInitializer : Initializer<WorkManager> {
    override fun create(context: Context): WorkManager {
        LogUtils.d("WorkManagerInitializer create ${Thread.currentThread().name}")
        val configuration = Configuration.Builder().build()
        WorkManager.initialize(context,configuration)
        return WorkManager.getInstance(context)

    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        LogUtils.d("WorkManagerInitializer dependencies ${Thread.currentThread().name}")
        return mutableListOf()
    }
}