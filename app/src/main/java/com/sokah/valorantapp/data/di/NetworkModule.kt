package com.sokah.valorantapp.data.di

import com.sokah.valorantapp.data.network.ValorantApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {
    const val BASEURL = "https://valorant-api.com/v1/"

    @Provides
    @Singleton
    fun provideRetrofit(): ValorantApi {

        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ValorantApi::class.java)
    }

}