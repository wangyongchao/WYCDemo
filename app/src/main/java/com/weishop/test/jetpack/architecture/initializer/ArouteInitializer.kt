package com.weishop.test.jetpack.architecture.initializer

import android.content.Context
import androidx.startup.Initializer
import com.weishop.test.util.LogUtils

class ArouteInitializer :Initializer<Unit> {
    override fun create(context: Context) {
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
       return mutableListOf()
    }
}