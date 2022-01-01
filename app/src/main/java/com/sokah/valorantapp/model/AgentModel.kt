package com.sokah.valorantapp.model


import com.google.gson.annotations.SerializedName

data class AgentModel(
    @SerializedName("abilities")
    val abilities: MutableList<Ability>,
    @SerializedName("assetPath")
    val assetPath: String,
    @SerializedName("bustPortrait")
    val bustPortrait: String,
    @SerializedName("characterTags")
    val characterTags: Any,
    @SerializedName("description")
    val description: String,
    @SerializedName("developerName")
    val developerName: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayIconSmall")
    val displayIconSmall: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("fullPortrait")
    val fullPortrait: String,
    @SerializedName("isAvailableForTest")
    val isAvailableForTest: Boolean,
    @SerializedName("isBaseContent")
    val isBaseContent: Boolean,
    @SerializedName("isFullPortraitRightFacing")
    val isFullPortraitRightFacing: Boolean,
    @SerializedName("isPlayableCharacter")
    val isPlayableCharacter: Boolean,
    @SerializedName("killfeedPortrait")
    val killfeedPortrait: String,
    @SerializedName("role")
    val role: Role,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("voiceLine")
    val voiceLine: VoiceLine
)