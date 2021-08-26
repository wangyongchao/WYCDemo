package com.kttest2.typesystem.primitive

class Person(val name:String,val age:Int?=null) {


    fun isOlderThan(other:Person):Boolean?{
        if(age==null || other.age==null){
            return null
        }

       return age > other.age
    }

}