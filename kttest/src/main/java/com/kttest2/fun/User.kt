package com.kttest2.`fun`

import java.lang.IllegalArgumentException

class User(val id: Int, val name: String, val address: String) {

    fun saveUser(user: User) {

        //需要重复验证名称，地址等字段，可以申请局部函数验证字段saveUserLocalFun
        if (user.name.isEmpty()) {
            throw IllegalArgumentException("Cant't save user ${user.id}:empty name")
        }

        if (user.address.isEmpty()) {
            throw IllegalArgumentException("Cant't save user ${user.id}:empty address")
        }
        //保存user到数据库
    }

    /**
     * 函数中嵌套函数，使用局部函数避免重复的代码
     * 进一步优化，使用扩展函数
     */
    fun saveUserLocalFun(user: User) {

        /**
         * 可以直接访问外部函数的变量
         */
        fun validate(value: String, fieldName: String) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Cant't save user ${user.id}:empty $fieldName")
            }
        }

        validate(user.name, "name")
        validate(user.address, "address")

//        validateBeforeStore() 使用扩展函数

        //保存user到数据库
    }

    /**
     * 使用扩展函数
     */
    fun User.validateBeforeStore() {
        /**
         * 可以直接访问外部函数的变量
         */
        fun validate(value: String, fieldName: String) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Cant't save user ${id}:empty $fieldName")
            }
        }

        validate(name, "name")
        validate(address, "address")
    }


}