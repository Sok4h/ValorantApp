package com.sokah.valorantapp.network

import com.sokah.valorantapp.model.AgentModel
import com.sokah.valorantapp.model.BaseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantApi {

    @GET("agents")
    suspend fun getAgents():Response <BaseModel<MutableList<AgentModel>>>

    @GET("agents/{agentUuid}")
    suspend fun getAgent(
        @Path("agentUuid") agentUuid:String
    ):Response <BaseModel<AgentModel>>


}