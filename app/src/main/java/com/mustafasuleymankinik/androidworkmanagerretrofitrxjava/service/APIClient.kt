package com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {


    var httpClient =  OkHttpClient.Builder().addInterceptor(Interceptor { chain: Interceptor.Chain ->
        return@Interceptor chain.proceed(chain.request().newBuilder()
            .addHeader("Authorization","apikey 1Bec0meWA7I0OGIBfLUtMi:78TcmOb6jvc8PkTKIrbEbH").build())
    })
    private const val BASE_URL="https://api.collectapi.com/"
    private val retrofit=Retrofit.Builder()
    fun  getClient():Retrofit{

        retrofit.baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return retrofit.build()
    }
}