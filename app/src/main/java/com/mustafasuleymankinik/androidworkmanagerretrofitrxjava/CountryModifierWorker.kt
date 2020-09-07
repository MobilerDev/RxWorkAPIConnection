package com.mustafasuleymankinik.androidworkmanagerretrofitrxjava

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.model.Country

const val COUNTRY_MODIFIER_WORKER_OUTPUT="CountryMWorker"
class CountryModifierWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {

        var gson=Gson()
        val country=gson.fromJson(inputData.getString(COUNTRY_WORKER_OUTPUT).toString(),Country::class.java)

        return Result.success(outputData(country))
    }

    private fun outputData(country: Country): Data {

        val float=country.country_population.toFloat()
        return Data.Builder().putFloat(COUNTRY_MODIFIER_WORKER_OUTPUT,float).build()
    }
}