package com.kttest.generics

/**
 * out修饰符我们可这样理解，
 * 当类、接口的泛型类型参数被声明为out时，则该类型参数是协变的，
 * 泛型类型的子类型是被保留的，它只能出现在函数的输出位置，只能作为返回类型，即生产者
 */
class GenericsTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            val arr = arrayOf(1, 2, 3)
            var array1: Array<TestB> = arrayOf(TestB(), TestB(), TestB())
            var array2: Array<*> = array1 //默认是不能型变的
//            val lista: List<TestB> = listOf(TestB(), TestB(), TestB())
//
//            val listb: List<TestA> = lista

            var arrayb: Array<TestB> = arrayOf(TestB(), TestB(), TestB())
            var arraya: Array<TestA> = arrayOf(TestA(), TestA(), TestA())
//            copy(arraya, arrayb)



        }

        fun copy(from: Array<out TestA>, to: Array<in TestA>) {
            for (i in from.indices) {
                to[i] = from[i]
            }
        }

        fun test(a: Comparable<TestA>) {
            val b: Comparable<TestB> = a
        }


        fun demo(strs: KSource<String>) {
            //只生产，不消费
            val objects: KSource<Any> = strs
        }

        fun demo(x: Comparable<Number>) {
            x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
            // 因此，我们可以将 x 赋给类型为 Comparable <Double> 的变量
            val y: Comparable<Double> = x // OK！
        }
    }
}