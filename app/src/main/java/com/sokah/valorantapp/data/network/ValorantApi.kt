package com.sokah.valorantapp.data.network

import com.sokah.valorantapp.data.model.BaseModel
import com.sokah.valorantapp.model.agents.AgentDto
import com.sokah.valorantapp.data.model.dtos.SkinDto
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


    @GET("weapons")
    suspend fun getWeapons(): Response<BaseModel<MutableList<WeaponDto>>>

    @GET("weapons/skins")
    suspend fun getSkins(): Response<BaseModel<MutableList<SkinDto>>>

    @GET("weapons/{weaponUuid}")

    suspend fun getWeapon(
        @Path("weaponUuid") weaponUuid: String
    ): Response<BaseModel<WeaponDto>>
}