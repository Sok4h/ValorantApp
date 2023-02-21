package com.sokah.valorantapp.data.repository

import com.sokah.valorantapp.ui.mapper.uiModel.WeaponModel
import com.sokah.valorantapp.data.model.entities.WeaponEntity

interface IWeaponRepository {


    suspend fun getAllWeaponsdb(): MutableList<WeaponModel>?
    suspend fun getAllWeapons(): MutableList<WeaponModel>?
    suspend fun addWeapons(weapons: MutableList<WeaponEntity>)
    suspend fun getWeaponByCategory(category: String): MutableList<WeaponModel>?

}