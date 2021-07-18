package com.kttest2.ktclass.`interface`

class FaceBookUser(val accountId: Int) : User {
    override val nickName = getFaceBookName(accountId)//只初始化一次

    private fun getFaceBookName(accountId: Int): String {
        println("getFaceBookName")
        return "facebook"
    }


}