package com.sokah.valorantapp.ui.mapper.uiModel



data class WeaponModel(

    val category: String,
    val defaultSkinUuid: String,
    val displayIcon: String,
    val displayName: String,
    val shopData: ShopDataModel?,
    val skins: MutableList<SkinModel>,
    val uuid: String,
    val weaponStats: WeaponStatsModel?
)

data class ShopDataModel(
    val cost: Int,
)



data class Chroma(

    val displayName: String,
    val fullRender: String,
    val streamedVideo: String,
    val swatch: String?,
    val uuid: String
)
data class WeaponStatsModel(

    val equipTimeSeconds: Double,
    val fireRate: Double?,
    val magazineSize: Int?,
    val reloadTimeSeconds: Double?,
)

data class Level(
    val assetPath: String,
    val displayIcon: String?,
)