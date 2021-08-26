package com.kttest2.typesystem.primitive

/**
 * 基本数据类型
 */
class PrimitiveTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {


        }

        fun testAny():Unit{
            //基本数据类型赋值会自动装箱
            val answer:Any=2
        }

        fun foo(l:Long){
            var b:Byte=1
            var l=b+1
            println(l)

        }

        /**
         * kotlin 数字转换不支持自动转换,必须显示的转换
         */
        fun numCast() {
            var i=1
            var l:Long=i.toLong()

        }

        fun showProgress(progress: Int) {
            val coerceIn = progress.coerceIn(0, 100)
            println("$coerceIn")

        }
    }


}