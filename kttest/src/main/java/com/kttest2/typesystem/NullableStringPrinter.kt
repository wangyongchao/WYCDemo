package com.kttest2.typesystem

class NullableStringPrinter : StringProcessor {
    override fun process(value: String?) {
        println("$value")
    }
}