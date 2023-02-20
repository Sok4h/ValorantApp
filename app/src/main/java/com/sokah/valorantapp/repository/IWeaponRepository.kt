package com.sokah.valorantapp.repository

import com.sokah.valorantapp.model.weapons.WeaponModel

interface IWeaponRepository {


    suspend fun getAllWeaponsdb(): MutableList<WeaponModel>?
    suspend fun getAllWeapons(): MutableList<WeaponModel>?
    suspend fun addWeapons(weapons: MutableList<WeaponModel>)
    suspend fun getWeaponByCategory(category: String): MutableList<WeaponModel>?

}