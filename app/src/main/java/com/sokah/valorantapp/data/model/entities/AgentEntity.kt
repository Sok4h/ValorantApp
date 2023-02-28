package com.sokah.valorantapp.data.model.entities


import androidx.room.*
import com.sokah.valorantapp.ui.mapper.uiModel.AbilityModel
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel
import com.sokah.valorantapp.ui.mapper.uiModel.RoleModel

@Entity(tableName = "agents")
data class AgentEntity(
    val abilities: MutableList<AbilityEntity>,
    val bustPortrait: String,
    val description: String,
    val developerName: String,
    val displayIcon: String?,
    val agentName: String,
    val fullPortrait: String,
    val killfeedPortrait: String,
    @Embedded
    val role: RoleEntity,
    @PrimaryKey(autoGenerate = false)
    val uuid: String,

    )

data class RoleEntity(

    val roleDescription: String,
    val roleIcon: String?,
    val roleName: String,
    val roleuuid: String
)



data class AbilityEntity(
    val description: String,
    val displayIcon: String?,
    val displayName: String,
    val slot: String
)



