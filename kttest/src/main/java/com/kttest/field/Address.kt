package com.kttest.field

import com.kttest.Customer

class Address {
    var state: String? = null

    //    var allByDefault:Int?
//    val simple:Int?
    var size = 3

    val Customer.isddd get() = 4

    fun copyAddress(address: Address): Address {
        val result = Address()
        result.state = address.state
        return result
    }

    val isEmpty get() = this.size == 0

    var stringRepresentation: String
        get() = this.toString()
        set(value) {
            println("value=${value}")
//            setDataFromString(value) // 解析字符串并赋值给其他属性
        }

    var setterVisibility: String = "abc"
        private set // 此 setter 是私有的并且有默认实现


}