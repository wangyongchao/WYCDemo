package com.kttest2.typesystem.collectionarray

import com.kttest2.zhinengzhuanhuan.Num
import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException

/**
 * 集合和数组
 */
class CollectionTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            testJavaKotlinCollections()
        }

        /**
         * java ,kotlin 集合互相调用
         * 再kotlin中是只读集合，传递给Java后就不能保证是只读集合，Java不区分只读集合和可变集合
         */
        fun testJavaKotlinCollections(){
            val list= listOf<String>("ddd","aaa","bbb")
            printInUppercase(list)

        }

        /**
         * 接受只读集合,但是调用java方法没法保证集合的只读性
         */
        fun printInUppercase(list:List<String>){
            println(CollectionsUtils.uppercaseAll(list))
            println(list.first())

        }

        fun testCopyElements() {
            val listOf = listOf(12, 3, 3)//只读变集合
            val source: Collection<Int> = arrayListOf(1, 2, 3, 4)//可变集合
            val target: MutableCollection<Int> = arrayListOf(8)
            copyElements(source, target)
            println(target)

        }

        /**
         * MutableCollectionn 不能接受只读集合 Collection
         */
        fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
            for (item in source) {
                target.add(item)
            }
        }


        fun readNumbersTest() {
            var reader = BufferedReader(StringReader("1\nabc\n42"))
            val readNumbers = readNumbers(reader)
            addValidNumbers1(readNumbers)
        }

        fun addValidNumbers(numbers: List<Int?>) {
            var sumOfValidNumbers = 0
            var invalidNumbers = 0
            for (number in numbers) {
                if (number != null) {
                    sumOfValidNumbers += number
                } else {
                    invalidNumbers++
                }
            }

            println("sumOfValidNumbers=${sumOfValidNumbers},invalidNumbers=${invalidNumbers}")
        }

        fun addValidNumbers1(numbers: List<Int?>) {
            var sumOfValidNumbers = 0
            var invalidNumbers = 0
            val validNumbers = numbers.filterNotNull()


            println("sumOfValidNumbers=${validNumbers.sum()},invalidNumbers=${numbers.count() - validNumbers.count()}")
        }

        fun readNumbers(reader: BufferedReader): List<Int?> {
            var result = ArrayList<Int?>()
            for (line in reader.lineSequence()) {
                try {
                    val number = line.toInt()
                    result.add(number)
                } catch (e: NumberFormatException) {
                    result.add(null)
                }
            }

            return result

        }
    }


}