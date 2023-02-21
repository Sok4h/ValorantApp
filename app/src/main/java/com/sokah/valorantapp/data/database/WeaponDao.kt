package com.sokah.valorantapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sokah.valorantapp.data.model.entities.WeaponEntity

@Dao
interface WeaponDao {


    @Query("SELECT * from weapons")
    suspend fun getAllWeapons(): MutableList<WeaponEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun insertWeapons(weapons:MutableList<WeaponEntity>)

    @Query("SELECT * from weapons where category LIKE :category")
    suspend fun getWeaponByCategory(category: String): MutableList<WeaponEntity>


}