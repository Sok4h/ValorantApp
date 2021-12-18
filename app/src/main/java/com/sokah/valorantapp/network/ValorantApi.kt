package com.sokah.valorantapp.network

import com.sokah.valorantapp.model.AgentList
import retrofit2.Response
import retrofit2.http.GET

interface ValorantApi {

    @GET("agents")
    suspend fun getAgents():Response <AgentList>
}