package com.sokah.valorantapp.network

import com.sokah.valorantapp.model.AgentModel
import com.sokah.valorantapp.model.BaseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ValorantApi {

    @GET("agents")
    suspend fun getAgents():Response <BaseModel<List<AgentModel>>>
}