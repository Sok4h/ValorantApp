package com.sokah.valorantapp.network

import android.util.Log
import com.sokah.valorantapp.model.AgentList
import com.sokah.valorantapp.model.AgentModel
import com.sokah.valorantapp.model.BaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class ValorantApiService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAgents(): BaseModel<List<AgentModel>> {

        return withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getAgents()


            response.body()!!
        }

    }

    suspend fun getAgent(agentUuid:String) :BaseModel<AgentModel>{

        return  withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getAgent(agentUuid)


            response.body()!!
        }

    }
}