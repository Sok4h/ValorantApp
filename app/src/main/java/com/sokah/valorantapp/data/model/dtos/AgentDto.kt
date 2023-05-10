package com.sokah.valorantapp.model.agents


import com.google.gson.annotations.SerializedName
import com.sokah.valorantapp.data.model.entities.AbilityEntity
import com.sokah.valorantapp.data.model.entities.AgentEntity
import com.sokah.valorantapp.data.model.entities.RoleEntity

data class AgentDto(
    @SerializedName("abilities")
    val abilities: MutableList<Ability>,
    val bustPortrait: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("developerName")
    val developerName: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayName")
    val agentName: String,
    @SerializedName("fullPortrait")
    val fullPortrait: String,
    @SerializedName("killfeedPortrait")
    val killfeedPortrait: String,
    @SerializedName("role")
    val role: Role,
    @SerializedName("uuid")
    val uuid: String,

    )

data class Role(
    @SerializedName("description")
    val roleDescription: String,
    @SerializedName("displayIcon")
    val roleIcon: String,
    @SerializedName("displayName")
    val roleName: String,
    @SerializedName("uuid")
    val roleuuid: String
)


data class Ability(
    @SerializedName("description")
    val description: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("slot")
    val slot: String
)

