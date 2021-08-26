package com.kttest2.collectionsregion

data class MutablePoint(var x:Int,var y:Int)

operator fun MutablePoint.set(index:Int,value:Int){
    when(index){
        0-> x=value
        1->y=value
        else -> throw IllegalAccessException("")
    }
}
