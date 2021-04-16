package com.kttest2.ktclass

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")

}

/**
 * 报错，public不能访问internal的
 *  fun TalkativeButton.giveSpeech(){
    yell()

}


 */

internal fun TalkativeButton.giveSpeech(){
}
