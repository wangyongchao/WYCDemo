package com.kttest

import com.kttest.data.Person1
import com.kttest.expand.Expand

class KTClient {

    companion object {

        /**
         * 主入口函数
         * @JvmStatic 只有加了这个注解我们才可以实现类似Java的静态方法的效果
         */
        @JvmStatic
        fun main(args: Array<String>) {
            val person1 = Person1("John")
            val person2 = Person1("John")
            person1.age = 10
            person2.age = 20
            val (name)=person1;

           print("${name}")

        }

        fun Expand.printFunctionType(i: Int) {
            println("Extension function")
        }


    }
}