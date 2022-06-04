package com.sokah.valorantapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sokah.valorantapp.model.weapons.WeaponModel

@Dao
interface WeaponDao {


    @Query("SELECT * from weapons")
    suspend fun getAllWeapons():MutableList<WeaponModel>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun insertWeapons(weapons:MutableList<WeaponModel>)

    @Query("SELECT * from weapons where category LIKE :category")
    suspend fun getWeaponByCategory(category:String):MutableList<WeaponModel>?
}