package com.kttest2.ktclass.`object`

class User private constructor(var nickname: String) {


    /**
     * 伴生对象 可以指定名字，默认名字是Companion
     * 相当于静态内部类 调用的时候可以直接通过类名.属性调用,User.newSubcribingUser
     */
    companion object {
        fun newSubcribingUser(email: String): User = User(email.substringBefore('@'))

        fun newFacebookUser(facebookAccountId: Int): User = User(getFaceBookName(facebookAccountId))


        private fun getFaceBookName(facebookAccountId: Int): String {
            return "facebook"
        }
    }


}