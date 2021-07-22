package com.kttest2.coroutine

import kotlinx.coroutines.*


fun log(msg:Any?){
    println("[${Thread.currentThread().name}] $msg")
}
class CoroutineTest {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            GlobalScope.launch(context = Dispatchers.IO){
                delay(1000)
                log("launch")
                fetchDocs()
            }

        }
        fun testScope(){
            GlobalScope.launch(context = Dispatchers.IO){
                delay(1000)
                log("launch")
            }
            Thread.sleep(2000)
            log("end")
        }
        suspend fun fetchDocs() {
            log("fetchDocs")
            val result = get("https://developer.android.com") // Dispatchers.IO for `get`
            log("result")                                    // Dispatchers.Main
        }

        suspend fun get(url: String) = withContext(Dispatchers.IO) {
            log("get before")
            delay(2000)
            log("get after")
        }


    }
}