package com.kttest2.`class`

interface Clickable {
    fun click()

    /**
     * 可以定义默认实现
     */
    fun showOff()= println("I'm Clickable")
}