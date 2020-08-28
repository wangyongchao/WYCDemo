package com.kttest

class Constructors {
    init {
        println("Init block")
    }

    /**
     * 隐式委托
     */
    constructor(i: Int) {
        println("Constructor")
    }
}