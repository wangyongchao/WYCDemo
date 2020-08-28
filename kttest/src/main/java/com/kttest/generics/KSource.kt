package com.kttest.generics

/**
 * 一般原则是：当一个类 C 的类型参数 T 被声明为 out 时，它就只能出现在 C 的成员的输出-位置，
 * 但回报是 C<Base> 可以安全地作为 C<Derived>的超类。
 * val c:C<Base> = C<Derived>
 */
interface KSource<out T> {
    fun nextT(): T
}