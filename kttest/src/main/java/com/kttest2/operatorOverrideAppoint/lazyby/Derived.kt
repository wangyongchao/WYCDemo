package com.kttest2.operatorOverrideAppoint.lazyby

/**
 * 委托给b可以不用复写基类中的方法
 * 如果重写了方法，不会再委托类b中再调用
 */
class Derived(b: Base) : Base by b {
    override val message: String = "Derived message"
    override fun printMessage() = print(message)

}