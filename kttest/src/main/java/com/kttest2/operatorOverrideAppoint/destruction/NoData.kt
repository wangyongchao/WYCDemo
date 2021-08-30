package com.kttest2.operatorOverrideAppoint.destruction

class NoData(var x: Int, var y: Int) {

    operator fun component1() = x
    operator fun component2() = y

}