package com.kttest2.ktclass

class SubcribingUser(override var email:String) : TestInterface {
    override var username: String
        get(){
            return email.substringBefore('@')
        }
        set(value) {}

}