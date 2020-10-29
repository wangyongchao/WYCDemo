package com.kttest2

enum class Color(var r: Int, var g: Int, var b: Int) {
    RED(255, 0, 0), ORANGE(255, 164, 0),
    YELLOW(255,255,0),GREEN(0,255,0);

    fun rgb() = (r * 256 + g) * 256 + b


}