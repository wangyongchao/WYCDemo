package com.kttest.inline

inline class Foo(val i: Int) : I {



    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val foo =Foo(3)
            asInline(foo)
            asGeneric(foo)
            asInterface(foo)
            asNullable(foo)
            id(foo)

        }


        fun asInline(f: Foo) {}

        fun <T> asGeneric(x: T) {}

        fun asInterface(i: I) {}

        fun asNullable(i: Foo?) {}

        fun <T> id(x: T): T = x
    }
}