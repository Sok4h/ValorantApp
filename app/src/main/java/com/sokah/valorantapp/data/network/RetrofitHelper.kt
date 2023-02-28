package com.sokah.valorantapp.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    const val BASEURL="https://valorant-api.com/v1/"

    fun getRetrofit(): Retrofit{

        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}