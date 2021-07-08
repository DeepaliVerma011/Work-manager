package com.deepaliverma.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubWorker(val context: Context, val param: WorkerParameters) :
    CoroutineWorker(context, param) {
    //In Build Coroutine Function
    override suspend fun doWork(): Result {
        val response = withContext(Dispatchers.IO) {
            Client.api().getUsers();
        }
        return if(response.isSuccessful){
            Log.i("Worker Request","Work Restarted")
            Result.retry()
        }
        else{
            Result.failure()
        }

    }


}