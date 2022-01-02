package com.sokah.valorantapp.network

import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.WeaponModel
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

    @GET("weapons")
    suspend fun getWeapons():Response <BaseModel<MutableList<WeaponModel>>>

}