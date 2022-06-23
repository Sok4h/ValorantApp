package com.sokah.valorantapp.network

import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.model.weapons.Skin
import com.sokah.valorantapp.model.weapons.WeaponModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ValorantApi {

    @GET("agents")
    suspend fun getAgents(
        @Query("language") language: String,
        @Query("isPlayableCharacter") isPlayableCharacter: Boolean = true
    ): Response<BaseModel<MutableList<AgentModel>>>

    /*  @GET("agents/{agentUuid}")
      suspend fun getAgent(
          @Path("agentUuid") agentUuid:String,
          @Query("language") language:String
      ):Response <BaseModel<AgentModel>>*/

    @GET("weapons")
    suspend fun getWeapons(): Response<BaseModel<MutableList<WeaponModel>>>

    @GET("weapons/skins")
    suspend fun getSkins(): Response<BaseModel<MutableList<Skin>>>

    @GET("weapons/{weaponUuid}")

    suspend fun getWeapon(
        @Path("weaponUuid") weaponUuid: String
    ): Response<BaseModel<WeaponModel>>
}