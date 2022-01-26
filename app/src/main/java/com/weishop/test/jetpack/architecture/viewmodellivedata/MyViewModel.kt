package com.weishop.test.jetpack.architecture.viewmodellivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    var name: MutableLiveData<String> = MutableLiveData()

    var currentValue=0

}