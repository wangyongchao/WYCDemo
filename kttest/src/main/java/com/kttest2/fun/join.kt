@file:JvmName("StringFunctions")

package strings

import kotlin.text.StringBuilder

var opCount = 0 //顶层属性，相当于java静态变量

fun performOperation() {
    opCount++
}

fun reportOperationCount() {
    println("Operation performed $opCount times")
}


/**
 * 顶层函数，编译完成后，默认使用文件名称作为类名称，要想修改类名称需要使用注解@file:JvmName("StringFunctions")
 *
 */
fun <T> joinToString(collection: Collection<T>, separator: String, prefix: String, postfix: String): String {
    var result = StringBuilder(prefix)
//解构声明写法
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()

}

/**
 * 扩展函数 String是要扩展的函数的对象,this指的是具体的字符串对象
 * 扩展函数是静态函数
 * 扩展函数不能被重写
 */
fun String.lastChar(): Char = this.get(this.length - 1)


//fun <T> Collection<T>.joinToString(separator: String, prefix: String, postfix: String): String {
//    var result = StringBuilder(prefix)
//
//    for ((index, element) in withIndex()) {
//        if (index > 0) result.append(separator)
//        result.append(element)
//    }
//    result.append(postfix)
//    return result.toString()
//
//}

//扩展属性
val String.lastCharFiled: Char get() = get(length - 1)

var StringBuilder.sblastChar: Char
    get() = get(length - 1)
    set(value) {
        this.setCharAt(length - 1, value)
    }


/**
 * infix 可以使用中缀方式调用函数
 */
infix fun Any.to(other: Any) = Pair(this, other)




