package com.kttest2.operatorOverrideAppoint.lazyby

class ByTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            testClassDelate()
        }

        fun testClassDelate() {
            var b = BaseImpl("BaseImpl")
            val derived = Derived(b)
            derived.printLine()
            derived.printMessage()

        }
    }
}