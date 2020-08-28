package com.kttest.java

import com.kttest.Person

class TestJavaKotlin {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var student =Student()
            student.name="32342"
            println(student.ddd)


        }

        private val Student.ddd: String
            get() {
                var mehod=Student::class.java.declaredMethods.first { it.name=="getName"  }
                mehod.isAccessible=true
                val invoke:String = mehod.invoke(this) as String

                return invoke
            }
    }


}