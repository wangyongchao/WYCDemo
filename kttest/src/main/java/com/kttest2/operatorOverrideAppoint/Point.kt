package com.kttest2.operatorOverrideAppoint

import java.lang.IndexOutOfBoundsException


/**
 * 数据类型默认生成解构声明
 */
data class Point(val x: Int, var y: Int) {




    /**
     * 用于重载运算符的所有函数都需要用operator关键字标记，
     * 用来表示你打算把这个函数作为相应的约定的实现，并且不是碰巧地定义一个同名函数
     */
    operator fun plus(other: Point): Point {
        println("plus成员函数")
        return Point(x + other.x, y + other.y)
    }


}


/**
 * 也可以定义为扩展函数
 * 同时定义成员函数和扩展函数，成员函数优先调用
 * "+"
 */
operator fun Point.plus(other: Point):Point{
    println("扩展函数 plus")
    return Point(x + other.x, y + other.y)
}

/**
 * 同时定义plus和plusAssign编译器会报错
 */
//operator fun Point.plusAssign(other: Point):Unit{
//    println("扩展函数 plusAssign")
//
//}

/**
 *重载乘法运算
 * "*"
 */
operator fun Point.times(scale: Double):Point{
    println("扩展函数 times")
    return Point((x*scale).toInt(),(y*scale).toInt())

}

/**
 *重载乘法运算
 * "*"
 */
operator fun Point.times(scale: Float):Point{
    println("扩展函数Float times")
    return Point((x*scale).toInt(),(y*scale).toInt())

}

/**
 * 重载一元运算符,函数没有参数
 */
operator fun Point.unaryMinus():Point{
    return Point(-x,-y)
}

operator fun Point.get(index:Int):Int{
    return when(index){
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException("Invaidate coordiate $index")
    }

}

