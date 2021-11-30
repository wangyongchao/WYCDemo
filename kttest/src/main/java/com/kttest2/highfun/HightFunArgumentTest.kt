package com.kttest2.highfun

import com.kttest2.lambda.LambdaTest
import com.kttest2.lambda.Person
import java.lang.StringBuilder

/**
 * 作为函数参数
 */
class HightFunArgumentTest {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            testRemoveDuplicatesCode()
        }

        /**
         * 高阶函数去除重复代码
         */
        fun testRemoveDuplicatesCode() {
            val log = listOf(
                SiteVisit("/", 34.0, OS.WINDOWS),
                SiteVisit("/", 23.0, OS.MAC),
                SiteVisit("/login", 39.0, OS.WINDOWS),
                SiteVisit("/singup", 100.0, OS.IOS),
                SiteVisit("/", 347.0, OS.ANDROID)
            )
            val result = log.filter { it.os == OS.WINDOWS }.map { it.duration }.average()
            println(result)
            log.averageDurationFor(OS.WINDOWS)
            log.averageDurationFor1 {
                it.os in setOf(OS.IOS, OS.ANDROID)
            }


        }


        /**
         * 函数类型可空
         */
        fun foo(callback: (() -> Unit)?) {
            println(callback?.invoke())
        }


        /**
         * 函数参数默认值
         */
        fun testFunDefaultValue() {
            val listOf = listOf(Person("zhangsan", 34), Person("lisi", 34))
            println(listOf.joinToString())

            println(listOf.joinToString { person: Person ->
                person.name
            })

        }


        /**
         * 测试从java中调用
         */
        fun processTheAnswer(f: (Int) -> Int) {
            println(f(12))
        }

        /**
         * 测试从java中调用
         */
        fun processTheJava(f: (Int) -> Unit) {
            println(f(12))
        }

        fun testCallHighFun() {


            val sum = { x: Int, y: Int -> x + y }//不声明类型
            val sum1: (Int, Int) -> Int = { x, y -> x + y }//显示类型
//            twoAndThree(sum)
            twoAndThree { x: Int, y: Int ->
                println(this)
                x + y
            }
            twoAndThree({ x: Int, y: Int ->
                println(this)
                x + y
            })
            println("a67ijj23fadj".filter { it in 'a' until 'z' })
        }

        fun twoAndThree(operation: (x: Int, Y: Int) -> Int) {
            println("$operation")
            println(operation(2, 3))
        }


        /**
         * 高阶函数就是以另一个函数作为参数或者返回值的函数。
         */
        fun testHIghFun() {
            val mutableListOf = mutableListOf<Int>(2, 3)
            mutableListOf.filter { it > 0 }

            val sum = { x: Int, y: Int -> x + y }//不声明类型
            val sum1: (Int, Int) -> Int = { x, y -> x + y }//显示类型
            println("$sum")

            val action = { print(12) }
            val action1: () -> Unit = { print(12) }//显示类型
            println("$action")

            //返回值类型可空
            val canReturnNull: (Int, Int) -> Int? = { _, _ -> null }
            val canReturnNull1 = canReturnNull(3, 4)
            println("${canReturnNull1.toString()}")

            //可空的函数类型的变量
            val funOrNUll: ((Int, Int) -> Int)? = null
            println("$funOrNUll")
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

fun String.filter(predicate: (Char) -> Boolean): String {

    var sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) {
            sb.append(element)
        }
    }

    return sb.toString()

}

/**
 * 函数类型的参数默认值
 */
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    transform: (T) -> String = { it.toString() }
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(transform(element))

    }

    result.append(postfix)
    return result.toString()

}

/**
 * 使用高阶函数抽取重复代码
 */
fun List<SiteVisit>.averageDurationFor(os: OS) =
    filter { it.os == os }.map(SiteVisit::duration).average()


/**
 * 使用高阶函数抽取重复代码
 */
fun List<SiteVisit>.averageDurationFor1(predicate: (SiteVisit) -> Boolean) =
    filter(predicate).map(SiteVisit::duration).average()