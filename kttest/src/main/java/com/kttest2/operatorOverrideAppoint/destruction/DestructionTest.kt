package com.kttest2.operatorOverrideAppoint.destruction

import com.kttest2.operatorOverrideAppoint.Point


/**
 * 解构声明
 */
class DestructionTest {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            testMap()

        }

        fun testMap(){
            val mapOf = mapOf<String, String>("1" to "A", "2" to "B")
            print(mapOf)


        }

        fun printMap(map:Map<String,String>){
            for((key,value ) in map){
                println("key=$key,value=$value")
            }
        }



        fun testSplitFileName(){
            val (name,ext) = splitFileName("test.txt")
            println("name=$name,ext=$ext")



        }

        fun splitFileName(fileName:String):NameComponents{
//            val result = fileName.split(".", limit = 2)
//            return NameComponents(result[0],result[1])
            var(name,ext)=fileName.split(".", limit = 2)
            return NameComponents(name,ext)

        }


        /**
         * 调用 componentN
         * x=p.component1()
         * y=p.component2()
         *
         * 解构声明主要应用从一个函数返回多个值
         */
        fun testDestruction(){
            //数据类型 data class 会默认生成解构函数
            val p=Point(12,30)
            val (x,y)=p
            println("$x,$y")

            val data=NoData(23,454)
            val (a,b)=data
            println("$a,$b")
        }
    }
}