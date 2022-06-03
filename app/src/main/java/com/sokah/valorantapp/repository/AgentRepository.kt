package com.sokah.valorantapp.repository

import android.util.Log
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.network.ValorantApiService
import retrofit2.HttpException
import java.io.IOException

class AgentRepository {

    private var service = ValorantApiService()

    suspend fun getAllAgents(): BaseModel<MutableList<AgentModel>>?{

        var result: BaseModel<MutableList<AgentModel>>?=null

        try {

            result =  service.getAgents()
            Log.e("TAG", result.toString())
        }catch (e: IOException) {

            Log.e("TAG", e.message.toString() )

        }catch (e: HttpException){

            Log.e("TAG", e.message.toString() )

        }

        return  result

    }
}