package com.kttest2.ktclass.by

/**
 * 委托 装饰器模式
 */
class CountingSet<T> (var innerSet:MutableCollection<T> = HashSet<T>()):MutableCollection<T> by innerSet{


    var objectsAdded=0
    override fun add(element: T): Boolean {
        objectsAdded++
       return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded+=elements.size
        return innerSet.addAll(elements)
    }

    companion object{

        @JvmStatic
        fun main(args: Array<String>) {
            var countingSet= CountingSet<Int>()
            countingSet.addAll(listOf(1,2,3))
            println("${countingSet.objectsAdded},size=${countingSet.size}")
        }
    }

}