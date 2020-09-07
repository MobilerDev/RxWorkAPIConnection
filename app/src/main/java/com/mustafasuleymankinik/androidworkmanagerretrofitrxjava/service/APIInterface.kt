package com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.service

import android.util.JsonToken
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.model.Continent
import com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.model.Country
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
//lateinit var token: String
interface APIInterface {
    @GET("run/5f45bc266559865775665611")
    fun getCountry(@Header("Authorization") string: String):Single<List<Country>>

    @GET("run/5f45309e6559865775665605")
    fun getContinent(@Header("Authorization") string: String):Single<List<Continent>>
}