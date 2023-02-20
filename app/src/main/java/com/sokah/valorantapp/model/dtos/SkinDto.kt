package com.sokah.valorantapp.model.dtos


import com.google.gson.annotations.SerializedName
import com.sokah.valorantapp.model.entities.SkinEntity
import com.sokah.valorantapp.model.weapons.Chroma
import com.sokah.valorantapp.model.weapons.Level

data class SkinDto(

    @SerializedName("chromas")
    val chromas: List<Chroma>,
    @SerializedName("displayIcon")
    val displayIcon: String?,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("levels")
    val levels: List<Level>,
    @SerializedName("themeUuid")
    val themeUuid: String,
    @SerializedName("uuid")
    val uuid: String,
)

fun SkinDto.toSkinEntity(): SkinEntity {

    return SkinEntity(

        chromas = chromas,
        displayIcon = displayIcon,
        displayName=displayName,
        levels=levels,
        themeUuid=themeUuid,
        uuid=uuid

    )
}