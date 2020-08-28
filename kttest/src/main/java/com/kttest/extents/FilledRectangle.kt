package com.kttest.extents

class FilledRectangle : Rectangle(), Polygon {
    override fun draw() {
        super<Rectangle>.draw()
        super<Polygon>.draw()
        println("Filling the rectangle")
    }

    val fillColor: String get() = super.borderColor

    inner class Filler {
        fun fill() {

        }

        fun drawAndFill() {
            super<Rectangle>@FilledRectangle.draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}")
            // 使用 Rectangle 所实现的 borderColor 的 get()
        }
    }

}