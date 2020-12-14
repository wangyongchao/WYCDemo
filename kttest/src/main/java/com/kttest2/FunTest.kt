package com.kttest

import java.lang.StringBuilder


class FunTest {


    companion object {

        /**
         * 主入口函数
         * @JvmStatic 只有加了这个注解我们才可以实现类似Java的静态方法的效果
         */
        @JvmStatic
        fun main(args: Array<String>) {
            testPrintSet()

        }

        fun testSet() {
            val hashSetOf = hashSetOf<Int>(2, 3, 4)
            println(hashSetOf.javaClass)//相当于Java 的getClass
            println(hashSetOf.max())
            val arrayListOf = arrayListOf(2, 3, 4)
            println(arrayListOf)
            println(arrayListOf.javaClass)
            val hashMapOf = hashMapOf(1 to "1", 2 to "2");
            println(hashMapOf.javaClass)


        }

        fun testPrintSet() {
            val arrayListOf = arrayListOf<Int>(2, 5, 6)
            println(joinToString(arrayListOf, ";", "(", ")"))

        }

        fun <T> joinToString(collection: Collection<T>, separator: String, prefix: String, postfix: String): String {
            var result = StringBuilder(prefix)

            for ((index, element) in collection.withIndex()) {
                if (index > 0) result.append(separator)
                result.append(element)
            }
            result.append(postfix)
            return result.toString()

        }


    }

}