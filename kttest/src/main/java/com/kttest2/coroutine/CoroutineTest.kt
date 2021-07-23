package com.kttest2.coroutine

import kotlinx.coroutines.*


fun log(msg: Any?) {
    println("[${Thread.currentThread().name}] $msg")
}

class CoroutineTest {

    companion object {

        /**
         * runBlocking 本身也是协程
         * 会只需完成runBlocking的代码再执行其后面的代码，
         * runBlocking里的任务如果是非常耗时的操作时，会一直阻塞当前线程，在实际开发中很少会用到runBlocking。
         */
        @JvmStatic
        fun main(args: Array<String>) {
            //协程还没有启动，主线程就结束，所以无法执行
//            GlobalScope.launch {
//                log("fdasf")
//            }

            runBlocking {
                log("start")
                val launch = launch(context = Dispatchers.IO) {
                    delay(1000L)
                    log("Hello")
                }
                Thread.sleep(2000L)
                log("World")

            }
            log("run bolock after")


        }


        /**
         * 可以轻松开启10万个协程,一个线程中可以有多个协程
         */
        fun startLaunch() {
            runBlocking {
                repeat(2) {//循环100000次
                    launch {//开启一个协程
                        log("launch")
                        delay(1000L)
                        print(".")
                    }

                }
            }

        }


    }
}