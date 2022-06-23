package com.sokah.valorantapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sokah.valorantapp.model.weapons.Skin

@Dao
interface  SkinDao {

    @Query("SELECT * from skins ")

    suspend fun getAllSkins():MutableList<Skin>

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun InsertSkins(skins:MutableList<Skin>)

    @Query("SELECT * from skins where displayName LIKE :type ")

    suspend fun getSkinByType(type:String):MutableList<Skin>

    @Query ("SELECT * from skins where uuid == :uuid")

    suspend fun getSkinByUuid(uuid:String):Skin

}