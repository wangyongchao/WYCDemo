package com.kttest2.ktclass.gettersetter

class LengthCounter {
    /**
     * 私有的不能被外部访问
     */
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter+= word.length
    }

    companion object{

        @JvmStatic
        fun main(args: Array<String>) {
            var lengthCounter=LengthCounter()
            lengthCounter.counter=3
        }


    }
}