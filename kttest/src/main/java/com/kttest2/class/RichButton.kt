package com.kttest2.`class`

/**
 * kotlin中默认都是final的不能被继承或者重写
 */
open class RichButton : Clickable {

    /**
     * 复写的方法，默认是open，如果不想让子类重写，需要加final
     */
    override fun click() {
        println("I'm RichButton")
    }

    fun disable() {}

    open fun animate() {}
}