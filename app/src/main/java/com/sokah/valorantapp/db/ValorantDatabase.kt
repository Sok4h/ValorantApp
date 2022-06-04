package com.sokah.valorantapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.model.weapons.WeaponModel


@Database(

    entities = [AgentModel::class, WeaponModel::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(
    AbilityConverter::class,
    RoleConverter::class,
    ShopDataConverter::class,
    SkinConverter::class,
    WeaponStatsConverter::class
)
abstract class ValorantDatabase : RoomDatabase() {

    abstract fun agentDao(): AgentDao
    abstract fun weaponDao(): WeaponDao

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
