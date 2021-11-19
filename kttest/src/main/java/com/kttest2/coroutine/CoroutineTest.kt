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
            test()


        }

        fun test() {
            runBlocking {
                println("${Thread.currentThread().name} 1")
                withContext(Dispatchers.IO){
                    delay(5000)
                    println("${Thread.currentThread().name} 2")

                }
                println("${Thread.currentThread().name} 3")
            }
        }

        /**
         * 协程启动模式
         * DEFAULT launch调用后，立即进入调度状态 .VM 上默认调度器的实现，就是开了一个线程池
         */
        fun startMode() {

            //default 模式
//            runBlocking {
//                log(1)
//
//                var job = GlobalScope.launch {
//                    log(2)
//                }
//                log(3)
//                job.join()
//                log(4)
//            }

            //start 方式 LAZY 懒汉式，需要的时候再去启动协程
//            runBlocking {
//                log(1)
//                val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
//                    log(2)
//                }
//                log(3)
//                job.start()//启动协程，启动成功返回true，已经启动过或者已经完成false
//                log(4)
//            }

            //join LAZY 懒汉式，需要的时候再去启动协程
            runBlocking {
                log(1)
                val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
                    log(2)
                }
                log(3)
                job.join()//挂起当前协程，直到job执行完成
                log(4)
            }

            //DEFAULT 立即执行协程体，无论什么时候都可以被取消，如果cpu还没有调度协程，但是已经执行了cancle方法，调度的时候也不会执行协程
//            runBlocking {
//                log("default 1")
//                val job = GlobalScope.launch(start = CoroutineStart.DEFAULT) {
//                    log("default 2")
//                    delay(1000)
//                    log("default 4")
//                }
//                job.cancel()//
//                log("default 3")
//            }
//            println("------------------")

            //ATOMIC 立即执行协程体，在协程开始运行之前无法取消，运行过程中可以取消
//            runBlocking {
//                log(1)
//                val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
//                    log(2)
//                    delay(1000)
//                    log(4)
//                }
//                job.cancel()//此方法调用的时候，有可能协程还没有被调度，所以取消失效。
//                log(3)
//            }

            //UNDISPATCHED 立即在当前线程执行协程体，碰到第一个suspend后会切换到其它线程执行后面的语句
//            runBlocking {
//                log(1)
//                val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
//                    log(2)
//                    delay(100)
//                    log(3)
//                }
//                log(4)
//                job.join()
//                log(5)
//            }

        }

        /**
         * 协程取消
         */
        fun cancelCoroutine() {
            runBlocking {
                var job = launch {
                    log("delay start")
                    delay(50000)
                    log("delay after")
                }
                delay(500)
                job.cancel()
                log("end")

            }
        }


        /**
         * 启动协程四种方式
         */
        fun startCoroutine() {
            //协程还没有启动，主线程就结束，所以无法执行,如果Thread.sleep(2000)阻塞住主线程此协程就会执行。
            //返回一个job
//            GlobalScope.launch {
//                delay(1000)
//                log("fdasf")
//            }
//            log("launch after")

//            runBlocking {
//                log("start")
//                val launch = launch(context = Dispatchers.IO) {
//                    delay(1000L)
//                    log("Hello")
//                }
//                Thread.sleep(2000L)
//                log("World")
//
//            }

            runBlocking {
                //异步启动协程,可以返回结果
                val async = async {
                    delay(50000)
                    log("async start")
                    return@async "hello"

                }
                log("main")
                val await = async.await()//会阻塞当前线程，等待结果返回
                log("await after")
                log(await)
            }


        }


        /**
         * 可以轻松开启10万个协程,一个线程中可以有多个协程
         */
        fun coroutineLaunch() {
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