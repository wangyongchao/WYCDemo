package com.kttest

import com.kttest2.`fun`.JavaToKotlin
import strings.lastCharFiled
import strings.sblastChar
import strings.to
import strings.lastChar as last


class FunTest {


    companion object {

        /**
         * 主入口函数
         * @JvmStatic 只有加了这个注解我们才可以实现类似Java的静态方法的效果
         */
        @JvmStatic
        fun main(args: Array<String>) {
            testParsePath()

        }

        fun testSet() {
            val hashSetOf = hashSetOf<Int>(2, 3, 4)
            println(hashSetOf.javaClass)//相当于Java 的getClass
            println(hashSetOf.maxOrNull())
            val arrayListOf = arrayListOf(2, 3, 4)
            println(arrayListOf)
            println(arrayListOf.javaClass)
            val hashMapOf = hashMapOf(1 to "1", 2 to "2");
            println(hashMapOf.javaClass)


        }

        fun testPrintSet() {
            val arrayListOf = arrayListOf<Int>(2, 5, 6)
//            println(joinToString(arrayListOf, ";", "(", ")"))
            println(joinToStringDefault(arrayListOf))

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

        /**
         * 函数参数可以携带默认值
         */
        fun <T> joinToStringDefault(collection: Collection<T>, separator: String = "， ", prefix: String = "(", postfix: String = ")"): String {
            var result = StringBuilder(prefix)

            for ((index, element) in collection.withIndex()) {
                if (index > 0) result.append(separator)
                result.append(element)
            }
            result.append(postfix)
            return result.toString()

        }


        /**
         * 顶层函数和属性
         */
        fun testTop() {
            val arrayListOf = arrayListOf<Int>(2, 5, 6)
            arrayListOf.joinToString()
            println(strings.joinToString(arrayListOf, ";", "(", ")"))
            JavaToKotlin.test()

        }


        /**
         * 扩展函数
         */
        fun testExtFun() {
            println("kotlin".last())
        }


        /**
         * 扩展函数 String是要扩展的函数的对象,this指的是具体的字符串对象
         */
        fun String.ext(): Char {
            return this.get(this.length - 1)
        }

        /**
         * 扩展属性
         */
        fun testExtField() {
            println("kotlin".lastCharFiled)
            val sb = StringBuilder("kotlin?")
            println(sb.sblastChar)
            sb.sblastChar = '!'
            println(sb.sblastChar)
//            JavaToKotlin.testCallField()

        }

        /**
         * 扩展集合，java中集合类在kotlin中的扩展
         */
        fun testSetFeature() {
            val hashSetOf = hashSetOf<Int>(2, 3, 4)
            println(hashSetOf.javaClass)//相当于Java 的getClass
            hashSetOf.maxOrNull()

            val arrayListOf = arrayListOf(2, 3, 4)
            arrayListOf.last()
            println(arrayListOf.javaClass)

            var arras = arrayOf("1", "2")
            //* 展开数组
            val arrayListOf1 = arrayListOf("2", *arras)
            println(arrayListOf1)

        }

        /**
         * 中缀调用
         * to 中缀调用
         */
        fun testZhongZhui(){
            val mapOf = mapOf(1 to "1", 2 to "2");
            var s="ss"
            val to = s to 2
            println(to.first)
            //直接中缀方式初始化pair 解构声明
            val (number,name)= 1 to "d"
            println(number)


        }

        /**
         * 可以直接传入字符串，java支持正则表达式
         */
        fun testString(){
            println("12.345-6.A".split("\\.|-".toRegex()))
            println("12.345-6.A".split(".","-"))
        }

        /**
         * 三元字符串 不用转义
         */
        fun testParsePath(){
            var path="/Users/yole/kotlin-book/chapter.adoc"
            val directory = path.substringBeforeLast("/")//返回最后一个"/"之前的字符串
            val fullName = path.substringAfterLast("/")//返回最后一个"/"之后的字符串
            val fileName = fullName.substringBeforeLast(".")
            val extName = fullName.substringAfterLast(".")
            println("directory=$directory,fullName=$fullName,fileName=$fileName,extName=$extName")

            val regex="""(.+)/(.+)\.(.+)""".toRegex()
            val matchEntire = regex.matchEntire(path)
            if(matchEntire!=null){
                val (directory, filename, extension) = matchEntire.destructured
                println("dir:$directory,name:$fileName,extension:$extension")
            }
        }

        /**
         * 局部函数
         */
        fun testLocalFun(){


        }




    }

}