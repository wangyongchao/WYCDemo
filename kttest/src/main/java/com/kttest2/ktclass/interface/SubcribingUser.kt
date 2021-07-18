package com.kttest2.ktclass.`interface`

class SubcribingUser(val email: String) : User {

    /**
     * 自定义getter,每次使用的时候都会调用
     */
    override val nickName: String
        get() {
            println("getter")
            return email.substringBefore('@')
        }

//    override val nickName: String
//        get()= email.substringBefore('@')
}