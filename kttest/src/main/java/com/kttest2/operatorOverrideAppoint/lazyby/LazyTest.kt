package com.kttest2.operatorOverrideAppoint.lazyby


/**
 * 惰性初始化，第一次访问该对象的时候才根据需要创建对象的一部分
 */
class LazyTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            testMap()

        }

        fun testMap(){
            val p=Person()
            val data = mapOf("name" to "Dmitry","company" to "google")
            for((attrName,value) in data){
                p.setAttributes(attrName,value)
            }
//            println("${p.name},${p.company1}")
        }

        fun testDelegat() {
            val p = Person1("zhangsan", 12)
            p.emails
            p.emails
        }

        fun testPropertyChange() {
            val p=PersonBy("zhangsan",23,4567)
            println(p.age)
            p.addPropertyChangeListener{
                println("Property ${it.propertyName} changed from${it.oldValue} to ${it.newValue}")
            }
            p.age=35
            println("${p.age},${p.salary}")

        }






    }
}