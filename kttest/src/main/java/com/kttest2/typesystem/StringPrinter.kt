package com.kttest2.typesystem

class StringPrinter : StringProcessor {
    /**
     * 被java调用会抛出异常
     */
    override fun process(value: String) {
        println("$value")
    }
}