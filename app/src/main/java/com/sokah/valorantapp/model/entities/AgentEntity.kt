package com.sokah.valorantapp.model.entities


import androidx.room.*
import com.sokah.valorantapp.model.dataModel.AbilityModel
import com.sokah.valorantapp.model.dataModel.AgentModel
import com.sokah.valorantapp.model.dataModel.RoleModel

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

fun RoleEntity.toRoleModel(): RoleModel {

    return RoleModel(
        this.roleDescription,
        this.roleIcon,
        this.roleName,
        this.roleuuid
    )
}

data class AbilityEntity(
    val description: String,
    val displayIcon: String?,
    val displayName: String,
    val slot: String
)

fun AbilityEntity.toAbilityModel(): AbilityModel {

    return AbilityModel(
        description,
        displayIcon,
        displayName,
        slot
    )

}

fun AgentEntity.toAgentModel(): AgentModel {

    return AgentModel(
        agentName = agentName,
        uuid = uuid,
        role = role.toRoleModel(),
        killfeedPortrait = killfeedPortrait,
        fullPortrait = fullPortrait,
        displayIcon = displayIcon,
        developerName = developerName,
        description = description,
        bustPortrait = bustPortrait,
        abilities = abilities.map { it.toAbilityModel() }.toMutableList()
    )
}