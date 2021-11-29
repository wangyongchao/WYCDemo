package com.kttest2.typesystem

/**
 * kotlin 类型系统
 * 注意必须使用问号结尾来标记类型为可空的，没有问号就是非空的。类型参数是这个规则唯一的例外。
 */
class TypeSystem {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            testNullable()

        }

        /**
         * 可空类型的扩展，只能是扩展函数
         * 为可空类型定义扩展函数是一种更强大的处理null值的方式。可以允许接收者为null的（扩展函数）调用，并在该函数中处理null，
         * 而不是在确保变量不为null之后再调用它的方法。只有扩展函数才能做到这一点，普通成员方法的调用是通过对象实例来分发的，
         * 因此实例为null时（成员方法）永远不能被执行。
         *
         * 可空类型的扩展函数中，this可能为null

         */
        fun testNullable() {
            verifyUserInput("")
            verifyUserInput(null)
            var email: String? = null
            //let 也能被可空的接受者调用
            //email?.let 不执行，email.let为null也执行
            email.let {
                //实参可能是null
                it?.length
                println("test $it")
            }
            printHashCode("null")


            var person: com.kttest2.lambda.Person? = null
            val result = person?.name ?: 0
            println(result)

        }

        /**
         * 泛型类型参数可null,推导出类型 Any?
         * 为类型T指明上界，就不能为Null
         */
        fun <T : Any> printHashCode(t: T) {
            println(t.hashCode())

        }


        /**
         *
         *
         *
         * 可null类型的扩展，
         * CharSequence?.isNullOrEmpty() null或者""
         *  CharSequence?.isNullOrBlank() null或者"  "
         */
        fun verifyUserInput(input: String?) {
            if (input.isNullOrBlank()) {
                println("isNullOrBlank")
            }
        }


        fun testLet() {
            var email: String? = "wangyongchao@163.com"
            email.let {

            }
            //email不为空执行，lambda。为空什么都不执行。
            var result = email?.let {
                sendEmail(email)
                3
            }

        }

        fun sendEmail(email: String) {
            println("send email $email")

        }

        fun ignoreNulls(s: String?) {
            val sNotNull: String = s!!//非null断言
            sNotNull.length
        }


        fun testElvis() {
            val address = Address("Elsestr. 47", 80687, "Munich", "Germany")
            val company = Company("JetBrains", address)
            val person = Person("Jons", company)

            printShippingLabel(person)
            printShippingLabel(Person("zhagnsan", null))

        }

        fun printShippingLabel(person: Person) {
            var address = person.company?.address ?: throw IllegalArgumentException("No Address")
            with(address) {
                println(streetAddress)
                println("$zipCode $city,$country")
            }
        }

        /**
         *
         */
        fun testNull() {
            println(strLen("null"))
            var x: String? = null
            println(x?.toUpperCase())
//            var y:String=x 可null类型的x不能赋值给y
            safeStrLen(x)
            printAllCaps(null)


        }

        /**
         * 默认是非空类型 strLen(null)会报错
         * 如果允许为空的话，需要指定String?
         * 没有？号的类型表示这种类型的变量不能存储null引用。说明所有常见类型，默认都是非null的。
         */
        fun strLen(s: String?) = s?.length

        fun safeStrLen(s: String?): Int = if (s != null) s.length else 0

        fun printAllCaps(s: String?) {
            val toUpperCase = s?.toUpperCase()
            println(toUpperCase)
        }

        fun foo(s: String?) {
            val t: String? = s ?: ""
        }
    }


}