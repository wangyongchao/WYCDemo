package com.kttest2.ktclass.`object`

interface JsonFactory<T> {
    fun fromJson(jsonText:String):T
}