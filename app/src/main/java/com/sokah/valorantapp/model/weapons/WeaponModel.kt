package com.sokah.valorantapp.model.weapons


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "weapons")
data class WeaponModel(
    @SerializedName("assetPath")
    val assetPath: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("defaultSkinUuid")
    val defaultSkinUuid: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("killStreamIcon")
    val killStreamIcon: String,
    @SerializedName("shopData")
    val shopData: ShopData?,
    @SerializedName("skins")
    val skins: MutableList<Skin>,
    @SerializedName("uuid")
    @PrimaryKey
    val uuid: String,
    @SerializedName("weaponStats")
    val weaponStats: WeaponStats?
)