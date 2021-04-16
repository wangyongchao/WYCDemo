package com.kttest2.ktclass

/**
 * 密封类
 */
sealed class Expr {
    class Num (val value:Int):Expr()
    class Sum(val left:Int,val right:Int):Expr()
//    class Dum(val value:Int):Expr()


}

