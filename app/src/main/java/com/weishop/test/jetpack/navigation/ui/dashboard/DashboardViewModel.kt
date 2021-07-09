package com.weishop.test.jetpack.navigation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    private var count:Int=0

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment${count++}"
    }
    val text: LiveData<String> = _text
}