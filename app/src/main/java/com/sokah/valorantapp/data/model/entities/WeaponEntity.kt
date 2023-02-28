package com.sokah.valorantapp.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sokah.valorantapp.ui.mapper.uiModel.ShopDataModel
import com.sokah.valorantapp.ui.mapper.uiModel.WeaponModel
import com.sokah.valorantapp.ui.mapper.uiModel.WeaponStatsModel

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


data class Level(
    val assetPath: String,
    val displayIcon: String?,
)
