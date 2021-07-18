package com.kttest2.ktclass.`object`

open class Person(val name: String) {
    /**
     * 可以指定伴生对象的名称，一个类中只能有一个伴生对象
     */
    companion object Loader : JsonFactory<Person> {


        @JvmStatic
        fun main(args: Array<String>) {
            Person.Loader.fromJson("")
            val loadFromJson = loadFromJson(Person)//传入的是伴生对象的实例,person被当做jsonfactory的实例
            println(loadFromJson.name)
            loadFromJson(object :JsonFactory<Person>{
                override fun fromJson(jsonText: String): Person {
                    TODO("Not yet implemented")
                }

            })
        }

        fun <T> loadFromJson(jsonFactory: JsonFactory<T>): T {
            return jsonFactory.fromJson("")
        }


        override fun fromJson(jsonText: String): Person {
            return Person("dfsadf")
        }
    }


}