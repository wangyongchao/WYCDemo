package com.kttest2.ktclass

interface TestInterface {
    var username:String
    var email:String
    val nickName:String
    get() = email.substringBefore('@')
}