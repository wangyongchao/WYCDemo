package com.kttest.extents

class Derived(name: String, var lastName: String) :
        Base(name.capitalize().also { println("Argument for Base: $it") }) {
    //capitalize 把首字母大写

    init {
        println("Initializing Derived")
    }

    override val size: Int = (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}