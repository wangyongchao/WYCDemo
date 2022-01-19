package com.weishop.test.jetpack.architecture.viewmodellivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private lateinit var name: MutableLiveData<String>

    fun getName(): MutableLiveData<String> {
        if (name == null) {
            name = MutableLiveData()
            name.value = "firstName"
        }
        return name

    }


}