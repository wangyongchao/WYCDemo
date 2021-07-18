package com.kttest2.ktclass.`object`

import com.kttest.Person
import java.io.File

/**
 * object 这个关键字定义一个类并同时创建一个实例
 */
class TestObject {

    /**
     * 相当于java中的单例，不允许有构造函数
     *使用对象名.属性来方法属性
     *   Payroll.persons
     */
    object Payroll{
        var persons = arrayListOf<Person>()

    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
//            Payroll.persons.add(Person("eewwer"))
//            val compare = CaseInsensitiveFileComparator.compare(File("/User"), File("/user"))
//            println(compare)
//           var files= listOf(File("/User"), File("/user"))
//            //可以把object 对象传入一个函数内
//            println(files.sortedWith(CaseInsensitiveFileComparator))
//
//            A.bar() //相当于静态方法或者静态变量


            val newFacebookUser = User.newFacebookUser(33)
            val newSubcribingUser = User.newSubcribingUser("sdafds@163.com")
            println("${newFacebookUser.nickname},${newSubcribingUser.nickname}")


        }



    }
}