package com.weishop.test.jetpack.architecture.viewmodellivedata

import android.app.Application
import android.text.TextUtils
import androidx.annotation.NonNull
import androidx.arch.core.util.Function
import androidx.lifecycle.*
import com.weishop.test.util.LogUtils

class NameViewModel : AndroidViewModel {
    var count: Int = 0
    var mutableLiveData = MutableLiveData<Int>()
    val switchMapLiveData:LiveData<Int>
    val saveStateLiveData:LiveData<String>

    constructor(@NonNull application: Application, @NonNull savedStateHandle: SavedStateHandle) : super(application) {
        LogUtils.d("constructor")

        //获取完成后，会直接调用会通知observer方法
        saveStateLiveData = savedStateHandle.getLiveData<String>("QUERY", null)

        switchMapLiveData = Transformations.switchMap(saveStateLiveData) { key ->
            if(TextUtils.isEmpty(key)){
                var nullData = MutableLiveData<Int>()
                nullData.value=0
                return@switchMap nullData
            }else{
                getSwitchName(key)
            }

        }
    }

    private fun getSwitchName(key: String): LiveData<Int> {
        LogUtils.d("getSwitchName")
        var liveData = MutableLiveData<Int>()
        liveData.value = key.toInt()
        return liveData
    }


    fun changeData() {
        currentName.value = "zhangsan" + count++
    }

    val currentName: MutableLiveData<String> by lazy {
        LogUtils.d("currentName livedata init")
        MutableLiveData<String>()
    }

    // 当currentName 的值改变的时候也会通知preCurrentName 改变
    val preCurrentName: LiveData<String> = Transformations.map(currentName) {
        "pre $it"
    }

    //必须返回livedata
    val sufCurrentName = Transformations.switchMap(currentName) {
        LogUtils.d("$it sufCurrentName")
        mutableLiveData.value = count
        mutableLiveData
    }

    fun acquirePostName(): LiveData<String> {
        LogUtils.d("acquirePostName")
        var liveData = MutableLiveData<String>()
        liveData.value = "zhangsan" + count++
        return liveData
    }


}