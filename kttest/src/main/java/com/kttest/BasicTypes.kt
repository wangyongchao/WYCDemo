package com.kttest


/**
 * 数字、字符、布尔值、数组与字符串
 */
class BasicTypes {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            testRetrun()

        }

        fun testNumbers() {
            val one = 1 //INT
            val threeBillion = 3000000000 //Long
            val oneLong = 1L //Long
            val oneByte: Byte = 1
            val pi = 3.14 //默认推断为double类型
            val e = 2.7182818284
            val eFloat = 2.7182818284f
            println("${eFloat}")

            val i = 1
            val d = 1.1
            var f = 1.1f
//            printDouble(i) //类型不匹配 不能隐式转换
//            printDouble(f)

        }

        fun printDouble(d: Double) {

        }

        /**
         * 字面常量
         * 十进制 123
         * 十六进制 0x0F
         * 二进制 0b000022
         * double 123.4
         * float 23.4f
         *数字装箱不保留同一性,它保留了相等性
         */
        fun testConstants() {
            var a: Int = 1000
            println(a === a)
            val boxedA: Int? = a
            println(a === boxedA)//false
            val anotherBoxedA: Int? = a
            println(boxedA === anotherBoxedA)//false
            println(boxedA == anotherBoxedA)// true

            val b: Byte = 1
//            val i:Int=b 不能隐式转换
            val toInt = b.toInt()//显示转换为int

            val l = 1L + 3//结果是Long

            val x = 5L / 2
            println(x == 2L)
            val y = 5 / 2.toDouble()
            println(y)

            val z = 1 shl 2
            println("${z}")

        }

        /**
         *字符
         */
        fun testChar() {
            val c: Char = '3'
            println(c.toInt() - '0'.toInt())//转换为int

        }

        fun testArray() {
            val asc = Array(5) { i ->
                (i * i).toString()
            }
            asc.forEach { println(it) }

            val x: IntArray = intArrayOf(1, 2, 3)
            var arr = IntArray(5)
            var arr1: IntArray = IntArray(5) {
                33
            }

            arr1.forEach { }
        }

        fun testString() {
            val str = "dsafadskf dsfadsf"
            for (c in str) {
//                println("c=${c}")
            }


            val s = "Hello, world!\n"
//            println("s=${s}")

            val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin("33")
            println(text)

            var ss = "abc"
            println("$ss.length is ${ss.length}")

        }

        /**
         * 控制流和表达式
         */
        fun testControlFlow() {
            val a = 3
            val b = 4
            val max = if (a > b) a else b
            println("max=${max}")

            //if 的分支可以是代码块，最后的表达式作为该块的值：

            val max1 = if (a > b) {
                println("Choose a")
                45
            } else {
                println("Choose b")
                99
            }
            println("max1=${max1}")

            //When 表达式
            val x = 3
            when (x) {
                1 -> print("x==1")
                2 -> print("x==2")
                3, 4 -> print("x==3 || x==4")
                "6".toInt() -> print("x==6")
                in 10..20 -> print("x in 10..20")
                else -> {
                    print("x is neither 1 nor 2")
                }
            }
            print("hasPrefix ${hasPrefix(6)}")


        }

        fun hasPrefix(y: Any) = when (y) {
            is String -> y.startsWith("pp")
            else -> true
        }

        fun testForEach() {
            var i = 2
            for (i in 1..3) {
                print("i=${i}")
            }

            println()

        }

        fun testRetrun(){
            foo2()
        }

        fun foo() {
            listOf(1, 2, 3, 4, 5).forEach lit@{
                if (it == 3) return@lit // 非局部直接返回到 foo() 的调用者
                print(it)
            }
            println("this point is unreachable")
        }

        fun foo1() {
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return@forEach // 非局部直接返回到 foo() 的调用者
                print(it)
            }
            println("this point is unreachable")
        }

        fun foo2() {
            listOf(1, 2, 3, 4, 5).forEach(fun(value:Int){
                if(value==3) return
                print(value)
            })
            println("this point is unreachable")
        }

    }


}

