package com.kttest2.`class`

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus")


    fun showOff()= println("I'm Focusable")

}