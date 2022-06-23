package com.sokah.valorantapp.model.weapons


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "skins")
data class Skin(
    @SerializedName("assetPath")
    val assetPath: String,
    @SerializedName("chromas")
    val chromas: List<Chroma>,
    @SerializedName("contentTierUuid")
    val contentTierUuid: String?,
    @SerializedName("displayIcon")
    val displayIcon: String?    ,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("levels")
    val levels: List<Level>,
    @SerializedName("themeUuid")
    val themeUuid: String,
    @SerializedName("uuid")
    @PrimaryKey
    val uuid: String,
    /*@SerializedName("wallpaper")
    val wallpaper: Any?*/
)