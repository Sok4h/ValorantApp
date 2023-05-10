package com.sokah.valorantapp.data.model

import com.sokah.valorantapp.data.model.dtos.SkinDto
import com.sokah.valorantapp.data.model.entities.*
import com.sokah.valorantapp.model.agents.Ability
import com.sokah.valorantapp.model.agents.AgentDto
import com.sokah.valorantapp.model.agents.Role
import com.sokah.valorantapp.model.weapons.ShopDataDto
import com.sokah.valorantapp.model.weapons.WeaponDto
import com.sokah.valorantapp.model.weapons.WeaponStatsDto

fun Role.toRoleEntity(): RoleEntity {

    return RoleEntity(

        roleDescription = roleDescription,
        roleIcon = roleIcon,
        roleName = roleName,
        roleuuid = roleuuid
    )
}


fun Ability.toAbilityEntity(): AbilityEntity {

    return AbilityEntity(
        description = description,
        displayIcon = displayIcon,
        slot = slot,
        displayName = displayName
    )
}

fun AgentDto.toAgentEntity(): AgentEntity {

    return AgentEntity(
        agentName = agentName,
        bustPortrait = bustPortrait,
        description = description,
        developerName = developerName,
        displayIcon = displayIcon,
        fullPortrait = fullPortrait,
        killfeedPortrait = killfeedPortrait,
        role = role.toRoleEntity(),
        uuid = uuid,
        abilities = abilities.map { it -> it.toAbilityEntity() }.toMutableList()
    )
}

//Skin


fun SkinDto.toSkinEntity(): SkinEntity {

    return SkinEntity(

        chromas = chromas,
        displayIcon = displayIcon,
        displayName = displayName,
        levels = levels,
        themeUuid = themeUuid,
        uuid = uuid

    )
}

//Weapon


fun WeaponStatsDto.toWeaponStatsEntity(): WeaponStatsEntity {

    return WeaponStatsEntity(
        equipTimeSeconds, fireRate, magazineSize, reloadTimeSeconds
    )
}

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


fun ShopDataDto.toShopDataEntity(): ShopDataEntity {

    return ShopDataEntity(cost)
}