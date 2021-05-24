package com.kttest2.ktclass

open class Button : Clickable, Focusable, View {

    var buttonState: ButtonState? = ButtonState()//声明的时候加上?表示这个类允许为空.!!表示   一定会抛出异常
    private lateinit var buttonState2: ButtonState

    override fun click() {
        println("button is clicked")
        println("after clicked ${buttonState2.printstate()}")//不会抛出异常，允许为空


    }

    /**
     * 如果实现两个接口都有此方法，需要重新定义
     */
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }


    /**
     * 不带修饰符，相当于java中的静态内部类
     * 如果需要存储外部类的引用需要使用inner 修饰
     */
    inner class ButtonState : State {

        fun printstate(): String {
            return "state"
        }

        fun getOutRefrence(): Button {
            return this@Button
        }

    }

    override fun getCurrentState(): State {
        return ButtonState()
    }


}