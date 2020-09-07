package com.mustafasuleymankinik.androidworkmanagerretrofitrxjava

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.model.Continent

const val CONTINENT_MODIFIER_WORKER_OUTPUT="ContinentMWorker"
class ContinentModifierWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        val gson= Gson()
        val continent=gson.fromJson(inputData.getString(CONTINENT_WORKER_OUTPUT).toString(),Continent::class.java)
        return Result.success(outputData(continent))
    }

    private fun outputData(continent: Continent): Data {
        val float=continent.continent_population.toFloat()
        return Data.Builder().putFloat(CONTINENT_MODIFIER_WORKER_OUTPUT,float).build()
    }
}