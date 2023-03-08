package com.sokah.valorantapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    const val BASEURL="https://valorant-api.com/v1/"

    fun getRetrofit(): ValorantApi {

        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ValorantApi::class.java)
    }

}