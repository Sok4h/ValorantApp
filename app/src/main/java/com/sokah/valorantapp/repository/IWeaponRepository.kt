package com.sokah.valorantapp.repository

import com.sokah.valorantapp.model.dataModel.WeaponModel
import com.sokah.valorantapp.model.entities.WeaponEntity

interface IWeaponRepository {


    suspend fun getAllWeaponsdb(): MutableList<WeaponModel>?
    suspend fun getAllWeapons(): MutableList<WeaponModel>?
    suspend fun addWeapons(weapons: MutableList<WeaponEntity>)
    suspend fun getWeaponByCategory(category: String): MutableList<WeaponModel>?

}