package com.kttest2.typesystem

class Person1(var firstName: String, var lastName: String) {

    override fun equals(other: Any?): Boolean {
        //other as? Person1 ,如果 other 是Person1 类型，就返回 other as Person1,如果不是就返回null
        var otherPerson = other as? Person1 ?: return false

        return otherPerson.firstName == firstName && otherPerson.lastName == lastName
    }

    override fun hashCode(): Int {
        return firstName.hashCode() * 37 + lastName.hashCode()
    }

}
