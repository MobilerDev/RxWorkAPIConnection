package com.mustafasuleymankinik.androidworkmanagerretrofitrxjava

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.*
import com.google.gson.Gson
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.model.Country
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.service.APIClient
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.service.APIInterface
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val COUNTRY_WORKER_OUTPUT="CountryWorker"
class CountryWorker(appContext: Context, workerParams: WorkerParameters) :
    RxWorker(appContext, workerParams)

{

    var country:Country?=null

    override fun createWork(): Single<Result> {
        val apiInterface=APIClient.getClient().create(APIInterface::class.java)
            .getCountry("apikey 1Bec0meWA7I0OGIBfLUtMi:78TcmOb6jvc8PkTKIrbEbH")
        return apiInterface
            .map {
                country=it.get(0)
               Result.success(outputData(country!!))
            }
    }
    private fun outputData(country: Country): Data {

        var gson:Gson= Gson()
        var jsonOutput=gson.toJson(country)
        return Data.Builder().putString(COUNTRY_WORKER_OUTPUT,jsonOutput).build()
    }
}