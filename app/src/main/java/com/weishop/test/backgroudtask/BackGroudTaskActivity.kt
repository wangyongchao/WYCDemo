package com.weishop.test.backgroudtask

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobServiceEngine
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weishop.test.backgroudtask.job.JobSchedulerService
import com.weishop.test.databinding.ActivityBackgroudtaskBinding
import com.weishop.test.util.LogUtils
import com.weishop.test.util.createIntent

class BackGroudTaskActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityBackgroudtaskBinding
    private val jobId = 1000
    private lateinit var jobScheduler: JobScheduler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBackgroudtaskBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        mBinding.start.setOnClickListener {
            startJob()
        }

        mBinding.stop.setOnClickListener {
            stopJob()

        }


    }

    private fun startJob() {


        var builder = JobInfo.Builder(jobId, ComponentName(this, JobSchedulerService::class.java))
        builder.setMinimumLatency(2000).setOverrideDeadline(10000)
        val result = jobScheduler.schedule(builder.build())
        if (result < 0) {
            LogUtils.d("job failed")
        }

    }

    private fun stopJob(){
        jobScheduler.cancel(jobId)
    }


}