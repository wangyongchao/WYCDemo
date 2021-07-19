package com.kttest2.ktclass

/**
 * 密封类 直接子类必须嵌套在父类中，sealed 修饰符隐含这是一个open类。
 */
sealed class Expr {
    class Num (val value:Int):Expr()
    class Sum(val left:Int,val right:Int):Expr()
//    class Dum(val value:Int):Expr()


}

