package com.sokah.valorantapp.data.repository

import com.sokah.valorantapp.data.model.entities.WeaponEntity
import com.sokah.valorantapp.ui.mapper.uiModel.WeaponModel

interface IWeaponRepository {


    suspend fun getAllWeaponsFromDatabase(): MutableList<WeaponModel>?
    suspend fun getAllWeapons(): Result<MutableList<WeaponModel>>
    suspend fun addWeapons(weapons: MutableList<WeaponEntity>)
    suspend fun getWeaponByCategory(category: String): MutableList<WeaponModel>

}