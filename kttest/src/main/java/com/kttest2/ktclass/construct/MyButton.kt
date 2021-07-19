package com.kttest2.ktclass.construct

class MyButton : View {

    constructor(ctx: String) : this(ctx,0) {
        println("MyButton  constructor(ctx: String)")

    }


    /**
     *必须显示的指定super
     *
     * 如果类没有主构造方法，那么每个从类必须初始化基类或委托给另一个这样做了的构造方法。
     */
    constructor(ctx: String, atr: Int) : super(ctx, atr) {
        println("MyButton   constructor(ctx: String, atr: Int) ")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var btn = MyButton("adsf");
        }
    }
}