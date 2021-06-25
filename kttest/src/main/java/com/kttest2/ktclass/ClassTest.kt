package com.kttest2.ktclass

class ClassTest {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            constructTest()
            args.map {  }



        }

        fun eval(e: Expr): Int =
                when (e) {
                    is Expr.Num -> e.value
                    is Expr.Sum -> e.left+e.right
                }

        fun constructTest(){
            var user:User= User("Alice")
            println("name=${user.name},isSubcribed=${user.isSubcribed},${user.address}")
            user.address="chuidenglu 45,444 ddd"
            println("${user.address}")

        }


    }
}