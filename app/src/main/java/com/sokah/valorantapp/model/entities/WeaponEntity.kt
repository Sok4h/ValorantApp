package com.sokah.valorantapp.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sokah.valorantapp.model.dataModel.ShopDataModel
import com.sokah.valorantapp.model.dataModel.WeaponModel
import com.sokah.valorantapp.model.dataModel.WeaponStatsModel

@Entity(tableName = "weapons")
data class WeaponEntity(

    val category: String,
    val defaultSkinUuid: String,
    val displayIcon: String,
    val displayName: String,
    val shopData: ShopDataEntity?,
    val skins: MutableList<SkinEntity>,
    @PrimaryKey
    val uuid: String,
    val weaponStats: WeaponStatsEntity?
)

data class ShopDataEntity(
    val cost: Int,
)
fun ShopDataEntity.toShopDataModel():ShopDataModel{

    return ShopDataModel(cost)
}

data class Chroma(

    val displayName: String,
    val fullRender: String,
    val streamedVideo: String,
    val swatch: String?,
    val uuid: String
)
data class WeaponStatsEntity(
    val equipTimeSeconds: Double,
    val fireRate: Double?,
    val magazineSize: Int?,
    val reloadTimeSeconds: Double?,
)

fun WeaponStatsEntity.toWeaponStatsModel(): WeaponStatsModel {

    return WeaponStatsModel(equipTimeSeconds,fireRate,magazineSize,reloadTimeSeconds)
}

data class Level(
    val assetPath: String,
    val displayIcon: String?,
)

fun WeaponEntity.toWeaponModel():WeaponModel{

    return WeaponModel(category,defaultSkinUuid,displayIcon,displayName,
        shopData?.toShopDataModel(),skins.map { it.toSkinModel() }.toMutableList(),uuid,weaponStats?.toWeaponStatsModel())
}