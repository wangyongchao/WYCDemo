package com.kttest2.operatorOverrideAppoint

class Person(var firstName:String,var lastName:String):Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this,other,Person::lastName,Person::firstName)
    }


}
