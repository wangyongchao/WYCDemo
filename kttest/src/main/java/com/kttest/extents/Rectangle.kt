package com.kttest.extents

open class Rectangle : Shape() {
    open override fun draw() {
        println("Drawing a rectangle")
    }

    val borderColor: String get() = "black"

    fun Shape.getName() = "Shape"

    fun Rectangle.getName() = "Rectangle"

    /**
     *   var rect = Rectangle()
    rect.printClassName(rect)
    打印结果是:Shape
    扩展函数是由参数类型所决定的
     */
    fun printClassName(s: Shape) {
        println(s.getName())
    }


}