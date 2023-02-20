package com.sokah.valorantapp.network

import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.agents.AgentDto
import com.sokah.valorantapp.model.dtos.SkinDto
import com.sokah.valorantapp.model.weapons.WeaponDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ValorantApi {

    @GET("agents")
    suspend fun getAgents(
        @Query("language") language: String,
        @Query("isPlayableCharacter") isPlayableCharacter: Boolean = true
    ): Response<BaseModel<MutableList<AgentDto>>>

    /*  @GET("agents/{agentUuid}")
      suspend fun getAgent(
          @Path("agentUuid") agentUuid:String,
          @Query("language") language:String
      ):Response <BaseModel<AgentModel>>*/

    @GET("weapons")
    suspend fun getWeapons(): Response<BaseModel<MutableList<WeaponDto>>>

    @GET("weapons/skins")
    suspend fun getSkins(): Response<BaseModel<MutableList<SkinDto>>>

    @GET("weapons/{weaponUuid}")

    suspend fun getWeapon(
        @Path("weaponUuid") weaponUuid: String
    ): Response<BaseModel<WeaponDto>>
}