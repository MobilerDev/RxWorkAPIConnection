package com.mustafasuleymankinik.androidworkmanagerretrofitrxjava

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.properties.Delegates

const val DETAIL_WORKER_OUTPUT="Detail_Output"
class DetailWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    var c3=1f
    var c4=0f

    override fun doWork(): Result {
        val value1=inputData.getFloat(COUNTRY_MODIFIER_WORKER_OUTPUT,0F)
        val value2=inputData.getFloat(CONTINENT_MODIFIER_WORKER_OUTPUT,0f)

        return Result.success(outputData(value1,value2))
    }

    private fun outputData(countryPopulation: Float, continentPopulation: Float): Data {

        val number:FloatArray= floatArrayOf(continentPopulation,countryPopulation)
        return  Data.Builder().putFloatArray(DETAIL_WORKER_OUTPUT,number).build()
    }
}