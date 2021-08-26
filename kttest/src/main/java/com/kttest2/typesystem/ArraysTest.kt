package com.kttest2.typesystem

/**
 * 数组
 */
class ArraysTest {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            var letters=Array<String>(26){ i->
                ('a'+i).toString()
            }
            println(letters.joinToString())

            val listOf = listOf("a", "b", "c")
            println("%s%s%s".format(*listOf.toTypedArray()))

            val intArray = IntArray(5)
            val intArrayOf = intArrayOf(0, 0, 0, 0, 0)


        }

    }

}