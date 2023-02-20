package com.sokah.valorantapp.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sokah.valorantapp.model.dataModel.SkinModel
import com.sokah.valorantapp.model.weapons.Chroma
import com.sokah.valorantapp.model.weapons.Level

@Entity(tableName = "skins")
data class SkinEntity(
    val chromas: List<Chroma>,
    val displayIcon: String?,
    val displayName: String,
    val levels: List<Level>,
    val themeUuid: String,
    @PrimaryKey
    val uuid: String,
)

fun SkinEntity.toSkinModel():SkinModel{

    return SkinModel(
        displayIcon = displayIcon,
        displayName = displayName,
        uuid = uuid,
        themeUuid = themeUuid,
        chromas = chromas,
        levels = levels

    )
}