package com.sokah.valorantapp.ui.mapper.uiMappers

import com.sokah.valorantapp.data.model.entities.*
import com.sokah.valorantapp.ui.mapper.uiModel.*


fun RoleEntity.toRoleModel(): RoleModel {

    return RoleModel(
        this.roleDescription,
        this.roleIcon,
        this.roleName,
        this.roleuuid
    )
}

fun AgentEntity.toAgentModel(): AgentModel {

    return AgentModel(
        agentName = agentName,
        uuid = uuid,
        role = role.toRoleModel(),
        killfeedPortrait = killfeedPortrait,
        fullPortrait = fullPortrait,
        displayIcon = displayIcon,
        developerName = developerName,
        description = description,
        bustPortrait = bustPortrait,
        abilities = abilities.map { it.toAbilityModel() }.toMutableList()
    )
}


fun AbilityEntity.toAbilityModel(): AbilityModel {

    return AbilityModel(
        description,
        displayIcon,
        displayName,
        slot
    )

}

//Skins

fun SkinEntity.toSkinModel(): SkinModel {

    return SkinModel(
        displayIcon = displayIcon,
        displayName = displayName,
        uuid = uuid,
        themeUuid = themeUuid,
        chromas = chromas,
        levels = levels

    )
}

//Weapon

fun ShopDataEntity.toShopDataModel(): ShopDataModel {

    return ShopDataModel(cost)
}


fun WeaponStatsEntity.toWeaponStatsModel(): WeaponStatsModel {

    return WeaponStatsModel(equipTimeSeconds, fireRate, magazineSize, reloadTimeSeconds)
}


fun WeaponEntity.toWeaponModel(): WeaponModel {

    return WeaponModel(
        category,
        defaultSkinUuid,
        displayIcon,
        displayName,
        shopData?.toShopDataModel(),
        skins.map { it.toSkinModel() }.toMutableList(),
        uuid,
        weaponStats?.toWeaponStatsModel()
    )
}