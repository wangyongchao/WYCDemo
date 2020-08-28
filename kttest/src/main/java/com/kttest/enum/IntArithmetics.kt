package com.kttest.enum

import android.os.Build
import android.support.annotation.RequiresApi
import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

@RequiresApi(Build.VERSION_CODES.N)
enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}