package com.kttest

/**
 * kotlin基础语法
 */
class BasicTest {

    companion object {

        /**
         * 主入口函数
         * @JvmStatic 只有加了这个注解我们才可以实现类似Java的静态方法的效果
         */
        @JvmStatic
        fun main(args: Array<String>) {
            val maxof = maxof(3,4)
            println(maxof)


        }


        val pi = 3.14
        var x = 0


        /**
         * 返回Int类型
         */
        fun sum(a: Int, b: Int): Int {
            return a + b;
        }

        /**
         * 将表达式作为函数体、返回值类型自动推断的函数：
         */
        fun sum1(a: Int, b: Int) = a + b;

        /**
         * 函数返回无意义的值 Unit可以省略
         */
        fun printsum(a: Int, b: Int): Unit {
            println("sum of $a and $b is ${a + b}")
        }

        /**
         * 定义只读局部变量使用关键字 val 定义。只能为其赋值一次
         * 可重新赋值的变量使用 var 关键字：
         */
        fun localViarable() {
            val a: Int = 3
            val b = 3
            val c: Int
            c = 3
            var d = 4
            d = 6
            println("a=$a,b=$b,c=$c,d=$d")
        }

        fun incrementX() {
            x += 1
            println("x= ${x}")
        }

        fun testString() {
            var a = 1
            val s1 = "a is $a"
            a = 2
            val s2 = "${s1.replace("is", "was")},but now is $a"

            println(s2)

        }

        fun maxof(a: Int, b: Int): Int {
            if(a>b){
                return a
            }else{
                return b
            }
        }


    }


}