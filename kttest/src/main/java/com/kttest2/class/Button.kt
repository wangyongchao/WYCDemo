package com.kttest2.`class`

class Button : Clickable,Focusable {
    override fun click() = println("button is clicked")

    /**
     * 如果实现两个接口都有此方法，需要重新定义
     */
    override fun showOff(){
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}