package com.kttest2.operatorOverrideAppoint.lazyby

class BaseImpl(var str: String) : Base {


    override val message: String = "baseimpl message"

    override fun printMessage()= print(str)

    override fun printLine()= println(message)
}