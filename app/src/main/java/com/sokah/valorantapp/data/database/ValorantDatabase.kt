package com.sokah.valorantapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sokah.valorantapp.data.model.entities.AgentEntity
import com.sokah.valorantapp.data.model.entities.SkinEntity
import com.sokah.valorantapp.data.model.entities.WeaponEntity


@Database(

    entities = [AgentEntity::class, WeaponEntity::class, SkinEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(
    AbilityConverter::class,
    RoleConverter::class,
    ShopDataConverter::class,
    SkinConverter::class,
    LevelConverter::class,
    WeaponStatsConverter::class,
    ChromaConverter::class
)
abstract class ValorantDatabase : RoomDatabase() {

    abstract fun agentDao(): AgentDao
    abstract fun weaponDao(): WeaponDao
    abstract fun skinDao(): SkinDao


}

