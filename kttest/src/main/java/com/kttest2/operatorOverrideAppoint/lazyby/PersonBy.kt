package com.kttest2.operatorOverrideAppoint.lazyby

import kotlin.properties.Delegates
import kotlin.reflect.KProperty


class PersonBy(val name: String, age: Int, salary: Int) : PropertyChangeAware() {

    /**
     * 使用getter/setter方式
     */
//    var age: Int = age
//        set(newValue) {
//            println(""" person $name : set age "$field" -> "$newValue"""".trimIndent())
//            val oldValue = field
//            field = newValue
//            changeSupport.firePropertyChange("age", oldValue, newValue)
//        }
//
//    var salary: Int = salary
//        set(newValue) {
//            val oldValue = field
//            field = newValue
//            changeSupport.firePropertyChange("salary", oldValue, newValue)
//        }


//    val _age=ObservableProperty("age",age,changeSupport)
//    var age:Int
//    get()=_age.getValue()
//    set(value){_age.setValue(value)}
//
//
//    val _salary=ObservableProperty("salary",salary,changeSupport)
//    var salary:Int
//        get()=_salary.getValue()
//        set(value){_salary.setValue(value)}


    private val observer = { property: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(property.name, oldValue, newValue)
    }

    /**
     * class C{
     * var prop:Type by MyDelegate():MyDelegate 中会有gevalue，setvalue方法。
     * 
     * }
     */
    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)


}
