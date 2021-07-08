package com.deepaliverma.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button.setOnClickListener {
            setupGithubWorker()
        }
    }

    private fun setupGithubWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)

            .build()
        val worker = OneTimeWorkRequestBuilder<GithubWorker>()
            .setInitialDelay(30,TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        val worker1 = PeriodicWorkRequestBuilder<GithubWorker>(1,TimeUnit.DAYS)
            .setInitialDelay(8,TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueue(worker1)
    }
}