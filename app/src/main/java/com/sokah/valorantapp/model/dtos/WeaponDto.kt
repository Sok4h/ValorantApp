package com.sokah.valorantapp.model.weapons


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sokah.valorantapp.model.dataModel.SkinModel
import com.sokah.valorantapp.model.dtos.SkinDto
import com.sokah.valorantapp.model.dtos.toSkinEntity
import com.sokah.valorantapp.model.entities.ShopDataEntity
import com.sokah.valorantapp.model.entities.WeaponEntity
import com.sokah.valorantapp.model.entities.WeaponStatsEntity

@Entity(tableName = "weapons")
data class WeaponDto(

    @SerializedName("category")
    val category: String,
    @SerializedName("defaultSkinUuid")
    val defaultSkinUuid: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("shopData")
    val shopData: ShopDataDto?,
    @SerializedName("skins")
    val skins: MutableList<SkinDto>,
    @SerializedName("uuid")
    @PrimaryKey
    val uuid: String,
    @SerializedName("weaponStats")
    val weaponStats: WeaponStatsDto?
)

data class ShopDataDto(


    @SerializedName("cost")
    val cost: Int,
)

fun ShopDataDto.toShopDataEntity(): ShopDataEntity {

    return ShopDataEntity(cost)
}

data class Chroma(

    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("fullRender")
    val fullRender: String,
    @SerializedName("streamedVideo")
    val streamedVideo: String,
    @SerializedName("swatch")
    val swatch: String?,
    @SerializedName("uuid")
    val uuid: String
)

data class WeaponStatsDto(

    @SerializedName("equipTimeSeconds")
    val equipTimeSeconds: Double,
    @SerializedName("fireRate")
    val fireRate: Double?,
    @SerializedName("magazineSize")
    val magazineSize: Int?,
    @SerializedName("reloadTimeSeconds")
    val reloadTimeSeconds: Double?,
)

fun WeaponStatsDto.toWeaponStatsEntity(): WeaponStatsEntity {

    return WeaponStatsEntity(
        equipTimeSeconds, fireRate, magazineSize, reloadTimeSeconds
    )
}

data class Level(
    @SerializedName("assetPath")
    val assetPath: String,
    @SerializedName("displayIcon")
    val displayIcon: String?,
)

fun WeaponDto.toWeaponEntity(): WeaponEntity {

    return WeaponEntity(
        category,
        defaultSkinUuid,
        displayIcon,
        displayName,
        shopData?.toShopDataEntity(),
        skins.map { it.toSkinEntity() }.toMutableList(),
        uuid, weaponStats?.toWeaponStatsEntity(),
    )
}