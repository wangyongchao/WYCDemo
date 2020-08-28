package com.kttest.expand

class Connection(val host: Host, val port: Int) {
    fun printPort() {
        print(port)
    }

    fun Host.printConnectionString() {
        printHostname()
        print(":")
        printPort()   // 调用 Connection.printPort()
    }

    fun connect() {
        /*……*/
        host.printConnectionString()   // 调用扩展函数
    }

}