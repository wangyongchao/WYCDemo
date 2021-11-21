package com.kttest2.highfun

import com.kttest2.lambda.LambdaTest
import com.kttest2.lambda.Person


class HightFunReturnTest {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
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

        fun testSimpleLambda() {
            val pepole = LambdaTest.initData()
            val maxBy = pepole.maxByOrNull(Person::age)
            println("$maxBy")
            //取最大值，不用简明语法
            pepole.maxByOrNull({ person: Person -> person.age })
            //如果lambda表达式是函数调用的最后一个实参，它可以放到括号的外边
            pepole.maxByOrNull() { person: Person -> person.age }
            //当lambda是函数唯一的实参时，还可以去掉调用代码中的空括号对
            pepole.maxByOrNull { person: Person -> person.age }
            //可以省略参数类型，和局部变量一样，如果lambda参数的类型可以被推导出来，你就不需要显式地指定它
            pepole.maxByOrNull { person -> person.age }

            //成员引用
            pepole.maxByOrNull(Person::age)

            //使用默认参数名称it代替命名参数。如果当前上下文期望的是只有一个参数的lambda且这个参数的类型可以推断出来，
            // 就会生成这个名称。仅在实参名称没有显式地指定时这个默认的名称才会生成。
            pepole.maxByOrNull { it.age }
            //如果你用变量存储lambda，那么就没有可以推断出参数类型的上下文。
            val getAge = { person: Person -> person.age }
            pepole.maxByOrNull(getAge)

            val joinToString =
                pepole.joinToString(separator = ",", transform = { person: Person -> person.name })
            println(joinToString)


            val sum = { x: Int, y: Int -> x + y } //lambda表达式存储在一个变量中，调用的时候直接指定变量的名字
            println(sum(3, 4))

            kotlin.run { println(42) }

        }
    }
}
