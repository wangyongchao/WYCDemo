package com.kttest2.ktclass

open class Button : Clickable, Focusable, View {

    var buttonState: ButtonState? = ButtonState()//声明的时候加上?表示这个类允许为空.!!表示一定会抛出异常
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


    class ButtonState : State {

        fun printstate() :String{
           return "state"
        }

    }

    override fun getCurrentState(): State {
        TODO("Not yet implemented")
    }


}