package com.sokah.valorantapp.network

import android.util.Log
import com.sokah.valorantapp.model.AgentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ValorantApiService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAgents(): AgentList {

        return withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getAgents()


            response.body()!!
        }

    }
}