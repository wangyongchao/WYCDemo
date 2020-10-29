package com.kttest2

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }

    val isSquare1:Boolean
    get() = height==width;
}