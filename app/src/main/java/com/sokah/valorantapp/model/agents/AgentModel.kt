package com.sokah.valorantapp.model.agents


import androidx.room.*
import com.google.gson.annotations.SerializedName
@Entity(tableName = "agents")
data class AgentModel(
    @SerializedName("abilities")

    val abilities: MutableList<Ability>,
   /* @SerializedName("assetPath")
    val assetPath: String,
    @SerializedName("bustPortrait")*/
    val bustPortrait: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("developerName")
    val developerName: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayIconSmall")
    val displayIconSmall: String,
    @SerializedName("displayName")
    val agentName: String,
    @SerializedName("fullPortrait")
    val fullPortrait: String,
    @SerializedName("isBaseContent")
    val isBaseContent: Boolean,
    @SerializedName("isFullPortraitRightFacing")
    val isFullPortraitRightFacing: Boolean,
    @SerializedName("isPlayableCharacter")
    val isPlayableCharacter: Boolean,
    @SerializedName("killfeedPortrait")
    val killfeedPortrait: String,
    @SerializedName("role")
    @Embedded
    val role: Role,
    @SerializedName("uuid")
    @PrimaryKey(autoGenerate = false)
    val uuid: String,

)