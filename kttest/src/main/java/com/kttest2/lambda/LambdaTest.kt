package com.kttest2.lambda

/**
 * Kotlin的lambda表达式始终用花括号包围
 */
class LambdaTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            testSimpleLambda()


        }

        fun testSimpleLambda(){
            val pepole = initData()
            val maxBy = pepole.maxByOrNull(Person::age)
            println("$maxBy")
            //取最大值，不用简明语法
            pepole.maxByOrNull({person: Person -> person.age })
            //如果lambda表达式是函数调用的最后一个实参，它可以放到括号的外边
            pepole.maxByOrNull() {person: Person -> person.age }
            //当lambda是函数唯一的实参时，还可以去掉调用代码中的空括号对
            pepole.maxByOrNull{person: Person -> person.age }
            //可以省略参数类型，和局部变量一样，如果lambda参数的类型可以被推导出来，你就不需要显式地指定它
            pepole.maxByOrNull{person -> person.age }
            //使用默认参数名称it代替命名参数。如果当前上下文期望的是只有一个参数的lambda且这个参数的类型可以推断出来，
            // 就会生成这个名称。仅在实参名称没有显式地指定时这个默认的名称才会生成。
            pepole.maxByOrNull{it.age }
            //如果你用变量存储lambda，那么就没有可以推断出参数类型的上下文。
            val getAge={person: Person -> person.age }
            pepole.maxByOrNull(getAge)

            val joinToString = pepole.joinToString(separator = ",", transform = { person: Person -> person.name })
            println(joinToString)


            val sum ={x:Int,y:Int ->x+y} //lambda表达式存储在一个变量中，调用的时候直接指定变量的名字
            println(sum(3,4))

            kotlin.run { println(42) }

        }


        fun initData():MutableList<Person>{
           var persons= MutableList<Person>(10,init = {
               Person("p$it",it+20)
           })
            return persons
        }

        fun findTheOldest(pepole: List<Person>) {
            var olderstPerson = Person("ceshi", -1)
            pepole.forEach {
                if (it.age > olderstPerson.age) {
                    olderstPerson = it
                }
            }
            if(olderstPerson.age!=-1){
                println("oldest ${olderstPerson.name}")
            }

        }


        fun reciver() {


            var sb = StringBuilder()

            val result = { sb: StringBuilder ->
                sb.append("111")
                sb.append(",222")
                sb.toString()
            }

            //如果lambda表达式是函数的最后一个实参，可以把{}写到()的外面,相当于上面得result
            /**
             *  A.() -> C  表示可以在A类型的接收者对象上调用并返回一个C类型值的函数。
             * with 表达式: public inline fun <T, R> with(receiver: T, block: T.() -> R): R
             * receiver: T sb代表接受者，block: T.() -> R 表示可以在StringBuilder的接受者对象上调用，并返回一个R值，此例子返回String
             * 调用lambda函数并把次函数的返回值作为最终返回值
             */
            val withSB = with(sb) {
                append("111")
                append(",222")
                this.toString()
            }
            println(withSB)

            /**
             * public inline fun <T> T.apply(block: T.() -> Unit): T
             * 始终返回T类型对象本身
             */
            var applyStringBuilder = StringBuilder().apply {
                append("333,")
                append("4444")

            }
            println(applyStringBuilder.toString())


            /**
             * public inline fun <T, R> T.let(block: (T) -> R): R
             * 传递T对象到lmbda函数中，然后返回R类型的值
             */
            var letSB = StringBuilder().let {
                it.append("let111")
                it.append(",let222")
                it.toString()

            }
            println(letSB)

            /**
             *public inline fun <T> T.also(block: (T) -> Unit): T
             */
            var alsoSB = StringBuilder().also {
                it.append("also 111")
                it.append(",also 222")

            }
            println(alsoSB.length)

            var funre = { a: Int ->
                println(a)
            }
            repeat(3, funre)


        }

        /**
         * //无参、无返回值的函数类型(Unit 返回类型不可省略)
        () -> Unit
        //接收T类型参数、无返回值的函数类型
        (T) -> Unit
        //接收T类型和A类型参数、无返回值的函数类型(多个参数同理)
        (T,A) -> Unit
        //接收T类型参数，并且返回R类型值的函数类型
        (T) -> R
        //接收T类型和A类型参数、并且返回R类型值的函数类型(多个参数同理)
        (T,A) -> R
         */

        fun testLambda() {
            val persons = listOf(Person("zhangsan", 23), Person("lisi", 26))
            //person 参数，Person参数类型
            val result = { person: Person ->
                person.age
            }
            val maxOf = persons.maxOf {
                //只有一个参数的时候，可以使用it
                it.age
            }
            print(maxOf)
        }

        fun compare(person: Person): Int {
            return person.age

        }
    }
}