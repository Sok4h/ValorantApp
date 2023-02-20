package com.sokah.valorantapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sokah.valorantapp.model.entities.AgentEntity
import com.sokah.valorantapp.model.entities.SkinEntity
import com.sokah.valorantapp.model.entities.WeaponEntity


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

    companion object {

        @Volatile
        private var INSTANCE: ValorantDatabase? = null

        fun getInstance(context: Context): ValorantDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            ValorantDatabase::class.java,
                            "myDB"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }

                return instance
            }
        }

    }
}
