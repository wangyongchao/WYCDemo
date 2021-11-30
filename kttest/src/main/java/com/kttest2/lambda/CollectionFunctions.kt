package com.kttest2.lambda

import com.kttest2.ktclass.construct.View
import java.io.File

/**
 * filter,map,“all”“any”“count”和“find”
 * groupBy,flatMap
 */
class CollectionFunctions {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            testMap()
        }

        /**
         * java 中的函数式接口
         * 当Java的接口中只有一个抽象方法。这种接口被称为函数式接口，或者SAM接口，SAM代表单抽象方法。
         * 可以使用lambda表达式
         */
        fun testJavaFunLambda(id: String) {
            var button = Button()

            button.setOnClickListener(object : Button.OnClickListener {
                override fun onClick(view: View?) {
                    println("onClick")
                }

            })
            button.setOnClickListener { view: View -> println("onClick") }
            button.setOnClickListener { view -> println("onClick") }
            /**
             * 这种方式可以工作的原因是OnClickListener接口只有一个抽象方法。这种接口被称为函数式接口，或者SAM接口，SAM代表单抽象方法。
             */
            button.setOnClickListener { println("onClick") }

            //多次重复调用会创建多个Runnable对象
            repeat(3) {
                button.postponeComputation(1000, object : Runnable {
                    override fun run() {
                        println("$this")
                    }

                })
            }
            var test = 3
            /**
             * 使用lambda表达式，如多次重复调用只会创建一个Runnable对象
             *
             */
            repeat(3) {
                button.postponeComputation(1000) {
                    println("$this,$id")
                }
            }

            //以上写法等同于以下写法，每次只会创建一个runnable对象
            var runnable = Runnable { println("$this,$id") }
            repeat(3) {
                button.postponeComputation(1000, runnable)
            }


        }


        /**
         * 创建序列
         */
        fun createSequence() {
            //生成一个序列
            val generateSequence = generateSequence(0) {
                println("generateSequence $it")
                it + 1
            }
            val takeWhile = generateSequence.takeWhile {
                println("takeWhile $it")
                it <= 100
            }
//            println(takeWhile.sum())

            //可以通过sequece遍历父目录
            var file = File("/Users/wangyongchao/Downloads/advanced1.json")
//            var file=File("/Users/wangyongchao/.gradle/6.5.1/gc.properties")
            println(file.isInsideHidderDirectory())

        }


        /**
         * 序列的执行顺序会影响性能
         */
        fun testSequenceExcOrder() {
            var pepole = listOf<Person>(
                Person("Alice", 20), Person("Bob", 35), Person("Tomator", 20)
            )
            println(pepole.asSequence().map { print("map(${it.name})");it.name }
                .filter { println(",filter(${it})"); it.length < 4 }.toList())
            println("----------------------------------------------------")

            /**
             * 先调用filter会减少比较的次数
             * 比较 "Alice"长度大于4个，就不会在转接到map集合中去比较,只有满足条件的才会转接到map集合中
             *
             */

            println(pepole.asSequence().filter { print("filter(${it.name})"); it.name.length < 4 }
                .map { println("map(${it.name})");it.name }.toList())

        }

        /**
         * 序列:提高链式调用的性能，链式调用会生成中间多个集合
         * Kotlin惰性集合操作的入口就是Sequence接口。这个接口表示的就是一个可以逐个列举元素的元素序列
         * squence的中间操作始终是惰性的，只有在获取结果的时候才会执行中间操作。获取结果的操作，包括list，find，first等操作。
         *
         */
        fun testSequence() {
            var pepole = listOf<Person>(
                Person("zhangsan1", 20), Person("lisi", 35), Person("zhangsan3", 20)
            )
            //中间map会生成一个集合，filter也会生成一个集合
            var list = pepole.map(Person::name).filter { it.startsWith("z") }
//            println(list)

            //转变成一个sequence,不会影响原来集合中的元素
            var sequence = pepole.asSequence().map(Person::name).filter { it.startsWith("z") }
//            println(sequence.toList())

            var sequence1 = pepole.asSequence().filter { it.name.startsWith("z") }
//            println(sequence1.toList())
            listOf(1, 2, 3, 4).map { println("map($it)");it * it }.filter { println("filter($it)");it % 2 == 0 }
            println("----------------------------------")
            /**
             *   squence中间操作(map ，filter)始终是惰性的,执行以下代码并不会打印，说明中间操作被延期了
             *  只有再获取结果的时候才会被调用，这就是惰性的意思。
             *  执行顺序 sequence是一个一个元素执行，先处理map，然后再处理filter。 map(1),filter(1);map(2),filter(4)
             *  集合是先把所有元素转出一个集合，然后再处理filter
             *  序列是惰性求值，集合是及早求值
             */
            listOf(1, 2, 3, 4).asSequence().map { print("map($it)");it * it }
                .filter { println(",filter($it)");it % 2 == 0 }.toList()
            println("----------------------------------")


            listOf(1, 2, 3, 4).map { print("map($it)");it * it }.find { println(",filter($it)");it > 3 }

            listOf(1, 2, 3, 4).asSequence().map { print("map($it)");it * it }.find { println(",filter($it)");it > 3 }


        }

        fun testFlat() {
            var book1 = Book("book1", listOf("author1-1", "author1-2"))
            var book2 = Book("book2", listOf("author2-1", "author2-2"))
            val books = listOf(book1, book2)
            // 把books集合中的每一个元素做变化，变化后放到一个新的集合中，最后返回集合
            var maps = books.flatMap { it.authors }
            println(maps)

            //flatMap接受的lamda表达式必须返回的是Iterable
            var maps1 = books.flatMap { listOf(it.title) }
            println(maps1)

            val strings = listOf("abc", "def")
            println(strings.flatMap { it.toList() })
            println(strings.flatMap(String::toList))//成员引用

        }

        /**
         * 按照某个表达式分组,返回一个map集合
         */
        fun testGroupBy() {
            var pepole = listOf<Person>(
                Person("zhangsan1", 20), Person("zhangsan2", 35), Person("zhangsan3", 20)
            )
            println(pepole.groupBy { it.age })

            val list = listOf<String>("a", "ab", "c", "cd")
            println(list.groupBy { str: String -> str.first() })
            println(list.groupBy(String::first))//成员引用，扩展函数

        }

        /**
         * 对集合应用判断
         * “all”“any”“count”和“find”
         */
        fun judgeCollection() {
            var pepole = listOf<Person>(Person("zhangsan1", 20), Person("zhangsan2", 35))
            val canBeInClub27 = { p: Person -> p.age <= 27 }
            //如果所有元素都满足条件返回true
            println(pepole.all(canBeInClub27))

            //至少有一个满足条件 就返回true
            println(pepole.any(canBeInClub27))

            //多少个元素满足了判断式，有多少个年龄小于27的
            println(pepole.count(canBeInClub27))

            //找到第一个满足判断式的元素,如果找不到就返回null
            println(pepole.find(canBeInClub27))


        }

        /**
         * map函数对集合中的每一个元素应用给定的函数并把结果收集到一个新集合
         */
        fun testMap() {
            val list = listOf<Int>(1, 2, 3, 4)
            var newList = list.map { it * it }
            println(newList)
            println(list)

            var pepole = listOf<Person>(Person("zhangsan1", 20), Person("zhangsan2", 35))
            println(pepole.map { it.name })
            println(pepole.map(Person::name))//可以使用成员引用

            //使用链条链接起来
            println(pepole.filter { it.age > 30 }.map { it.name })

            val mapOf = mapOf(0 to "zero", 1 to "one")
            //对map应用过滤和变换转换
//            mapOf.filterValues {  }
            println(mapOf.mapValues { it.value.uppercase() })


        }

        /**
         * filter产生一个新的集合，并不会改变原来集合的元素
         * filter函数可以从集合中移除你不想要的元素，但是它并不会改变这些元素
         */
        fun testFilter() {
            val list = listOf<Int>(1, 2, 3, 4)
            val filter = list.filter { it % 2 == 0 }
            println(filter)
            println(list)

            val listOf = listOf(Person("zhangsan1", 20), Person("zhangsan2", 34))
            println(listOf.filter {
                it.age > 30
            })
            println(listOf)
        }
    }

}

fun File.isInsideHidderDirectory() = generateSequence(this) {
    println("parentFile=${it.parentFile}")
    it.parentFile
}.any {
    "isHidden=${it.isHidden}"
    it.isHidden
}
