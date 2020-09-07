package com.mustafasuleymankinik.androidworkmanagerretrofitrxjava

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.RxWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.model.Continent
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.model.Country
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.service.APIClient
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.service.APIInterface
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

const val CONTINENT_WORKER_OUTPUT="ContinentWorker"
class ContinentWorker(appContext: Context, workerParams: WorkerParameters) :
    RxWorker(appContext, workerParams) {
    var continent:Continent?=null


    override fun createWork(): Single<Result> {
        val apıInterface=APIClient.getClient().create(APIInterface::class.java)
            .getContinent("apikey 1Bec0meWA7I0OGIBfLUtMi:78TcmOb6jvc8PkTKIrbEbH")
            return apıInterface.map {
                continent=it.get(1)
                Result.success(outputData(continent!!))
            }
    }
    private fun outputData(continent: Continent): Data {
        var gson: Gson = Gson()
        var jsonOutput=gson.toJson(continent)
        return Data.Builder().putString(CONTINENT_WORKER_OUTPUT,jsonOutput).build()
    }
}