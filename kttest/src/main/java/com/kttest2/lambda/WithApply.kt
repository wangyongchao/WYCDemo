package com.kttest2.lambda

class WithApply {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            testApply()

        }


        fun testApply() {
            println(buidStringAlphabet())


        }

        fun testWith() {
            println(alphabet())
            println(withalphabet())
            println(withalphabet2())

        }

        fun alphabet(): String {
            var result = StringBuilder()
            for (letter in 'A'..'Z') {
                result.append(letter)
            }
            result.append("\nNow I know the alphabet")
            return result.toString()
        }

        /**
         * alphabet函数多次调用result，result会重复出现
         * 使用with改造,lambda表达式内传递了接受者对象，返回另一个结果
         */
        fun withalphabet(): String {
            var result = StringBuilder()
            return with(result) {
                for (letter in 'A'..'Z') {
                    append(letter)
                }
                append("\nNow I know the alphabet")
                toString()
            }
        }

        /**
         * alphabet函数多次调用result，result会重复出现
         * 使用with改造
         */
        fun withalphabet2() = with(StringBuilder()) {
            for (letter in 'A'..'Z') {
                append(letter)
            }
            append("\nNow I know the alphabet")
            toString()
        }

        /**
         * 始终返回的是接受者对象
         */
        fun applyAlphabet() = StringBuilder().apply {
            for (letter in 'A'..'Z') {
                append(letter)
            }
            append("\nNow I know the alphabet")
        }.toString()

        fun buidStringAlphabet() = buildString {
            for (letter in 'A'..'Z') {
                append(letter)
            }
            append("\nNow I know the alphabet")
        }
    }


}