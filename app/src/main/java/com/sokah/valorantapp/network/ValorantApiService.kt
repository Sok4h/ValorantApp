package com.sokah.valorantapp.network

import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.WeaponModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ValorantApiService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAgents(): BaseModel<MutableList<AgentModel>> {

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

    suspend fun getWeapons():BaseModel<MutableList<WeaponModel>>{

        return withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getWeapons()
            response.body()!!
        }
    }
}