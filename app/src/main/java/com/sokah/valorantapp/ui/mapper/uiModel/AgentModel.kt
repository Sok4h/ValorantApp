package com.sokah.valorantapp.ui.mapper.uiModel

data class AgentModel(
    val abilities: MutableList<AbilityModel>,
    val bustPortrait: String,
    val description: String,
    val developerName: String,
    val displayIcon: String?,
    val agentName: String,
    val fullPortrait: String,
    val killfeedPortrait: String,
    val role: RoleModel,
    val uuid: String,

    )
data class RoleModel(

    val roleDescription: String,
    val roleIcon: String?,
    val roleName: String,
    val roleuuid: String
)

data class AbilityModel(
    val description: String,
    val displayIcon: String?,
    val displayName: String,
    val slot: String
)