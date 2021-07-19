package com.kttest2.ktclass.construct

open class View {

    constructor(ctx: String) {
        println("View  constructor(ctx: String)")

    }

    constructor(ctx: String, atr: Int) {
        println("View   constructor(ctx: String, atr: Int) ")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

        }
    }


}