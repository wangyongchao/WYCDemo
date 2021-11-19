package com.weishop.test.backgroudtask.job

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.weishop.test.util.LogUtils
import kotlinx.coroutines.*

var job: Job? = null

class JobSchedulerService : JobService() {


    val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            LogUtils.d("handleMessage")

        }
    }

    /**
     * 返回false 说明job已经完成，不是个耗时的任务，job会立即执行完毕
     * 返回true 说明job在异步执行，是个耗时任务，需要手动调用jobFinished告诉系统job执行完成
     */
    override fun onStartJob(params: JobParameters?): Boolean {
        LogUtils.d("onStartJob")

//        Thread{
//            Thread.sleep(20*1000)
//            LogUtils.d("sleep")
//            jobFinished(params,false)
//        }.start()
//        if (job != null && job!!.isActive) {
//            LogUtils.d("job cancle")
//            job?.cancel()
//        }
        job = GlobalScope.launch(Dispatchers.Main) {
            delay(20 * 1000)
            LogUtils.d("delay 5")
        }
//        handler.sendMessageDelayed(Message.obtain(handler, 20, params),60*1000) 并不会造成泄漏
        return true
    }

    /**
     *当系统收到一个cancel job的请求时，并且这个job仍然在执行(onStartJob返回true)，系统就会调用onStopJob方法。
     *但不管是否调用onStopJob，系统只要收到取消请求，都会取消该job
     *
     * 返回true 需要重试
     * 返回false，不需要重试，直接丢弃
     */
    override fun onStopJob(params: JobParameters?): Boolean {
        LogUtils.d("onStopJob")

        return false
    }

    override fun onCreate() {
        super.onCreate()
        LogUtils.d("onCreate $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("onDestroy $this")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtils.d("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

}