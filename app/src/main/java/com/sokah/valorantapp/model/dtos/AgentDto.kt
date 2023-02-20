package com.sokah.valorantapp.model.agents


import com.google.gson.annotations.SerializedName
import com.sokah.valorantapp.model.entities.AbilityEntity
import com.sokah.valorantapp.model.entities.AgentEntity
import com.sokah.valorantapp.model.entities.RoleEntity

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
fun Role.toRoleEntity(): RoleEntity {

    return  RoleEntity(

        roleDescription=roleDescription,
        roleIcon=roleIcon,
        roleName=roleName,
        roleuuid =roleuuid
    )
}

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
fun Ability.toAbilityEntity(): AbilityEntity {

    return AbilityEntity(
        description=description,
        displayIcon=displayIcon,
        slot =slot,
        displayName = displayName
    )
}

fun AgentDto.toAgentEntity():AgentEntity{

    return  AgentEntity(
        agentName = agentName,
        bustPortrait = bustPortrait,
        description = description,
        developerName = developerName,
        displayIcon = displayIcon,
        fullPortrait = fullPortrait,
        killfeedPortrait = killfeedPortrait,
        role = role.toRoleEntity(),
        uuid = uuid,
        abilities = abilities.map { it-> it.toAbilityEntity() }.toMutableList()
    )
}