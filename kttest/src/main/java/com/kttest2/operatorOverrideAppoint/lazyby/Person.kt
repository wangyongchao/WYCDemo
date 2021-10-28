package com.kttest2.operatorOverrideAppoint.lazyby

class Person {
    private val _attributes= hashMapOf<String,String>()

    fun setAttributes(name:String,value:String){
        _attributes[name]=value
    }

//    val name:String
//    get() = _attributes["name"]!!

    //直接委托给_attributes,属性名称用做map中的键
    val name:String by _attributes
    val company:String by _attributes


}