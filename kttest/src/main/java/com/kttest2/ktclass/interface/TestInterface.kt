package com.kttest2.ktclass.`interface`

class TestInterface {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var privateUser = FaceBookUser(333)
            println("${privateUser.nickName}")
            println("${privateUser.nickName}")
        }
    }
}