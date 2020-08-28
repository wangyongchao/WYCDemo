package com.kttest.extents

/**
 * kotlin 所有类都默认继承Any
 * 类默认是final，是不能被继承的，如果需要被继承，加open关键字
 *
 */
open class Base(var name: String) {
    init {
        println("Initializing Base")
    }

    open val size: Int = name.length.also { println("Initializing size in Base: $it") }

}