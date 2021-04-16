package com.kttest2.ktclass

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {

    }
}