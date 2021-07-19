package com.kttest2.`fun`.lambda

import android.util.StringBuilderPrinter
import kotlin.text.StringBuilder

class LambdaTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            reciver()
        }


        fun reciver() {


            var sb = StringBuilder()

            val result = { sb: StringBuilder ->
                sb.append("111")
                sb.append(",222")
                sb.toString()
            }

            //如果lambda表达式是函数的最后一个实参，可以把{}写到()的外面,相当于上面得result
            /**
             *  A.() -> C  表示可以在A类型的接收者对象上调用并返回一个C类型值的函数。
             * with 表达式: public inline fun <T, R> with(receiver: T, block: T.() -> R): R
             * receiver: T sb代表接受者，block: T.() -> R 表示可以在StringBuilder的接受者对象上调用，并返回一个R值，此例子返回String
             * 调用lambda函数并把次函数的返回值作为最终返回值
             */
            val withSB = with(sb) {
                append("111")
                append(",222")
                this.toString()
            }
            println(withSB)

            /**
             * public inline fun <T> T.apply(block: T.() -> Unit): T
             * 始终返回T类型对象本身
             */
            var applyStringBuilder = StringBuilder().apply {
                append("333,")
                append("4444")

            }
            println(applyStringBuilder.toString())


            /**
             * public inline fun <T, R> T.let(block: (T) -> R): R
             * 传递T对象到lmbda函数中，然后返回R类型的值
             */
            var letSB = StringBuilder().let {
                it.append("let111")
                it.append(",let222")
                it.toString()

            }
            println(letSB)

            /**
             *public inline fun <T> T.also(block: (T) -> Unit): T
             */
            var alsoSB = StringBuilder().also {
                it.append("also 111")
                it.append(",also 222")

            }
            println(alsoSB.length)

            var funre = { a: Int ->
                println(a)
            }
            repeat(3,funre)


        }

        /**
         * //无参、无返回值的函数类型(Unit 返回类型不可省略)
        () -> Unit
        //接收T类型参数、无返回值的函数类型
        (T) -> Unit
        //接收T类型和A类型参数、无返回值的函数类型(多个参数同理)
        (T,A) -> Unit
        //接收T类型参数，并且返回R类型值的函数类型
        (T) -> R
        //接收T类型和A类型参数、并且返回R类型值的函数类型(多个参数同理)
        (T,A) -> R
         */

        fun testLambda() {
            val persons = listOf(Person("zhangsan", 23), Person("lisi", 26))
            //person 参数，Person参数类型
            val result = { person: Person ->
                person.age
            }
            val maxOf = persons.maxOf {
                //只有一个参数的时候，可以使用it
                it.age
            }
            print(maxOf)
        }

        fun compare(person: Person): Int {
            return person.age

        }
    }
}