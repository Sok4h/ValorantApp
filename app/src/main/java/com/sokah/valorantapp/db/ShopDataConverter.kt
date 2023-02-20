package com.sokah.valorantapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sokah.valorantapp.model.entities.ShopDataEntity
import com.sokah.valorantapp.model.entities.SkinEntity
import com.sokah.valorantapp.model.entities.WeaponStatsEntity

class ShopDataConverter {

    val gson = Gson()

    @TypeConverter
    fun fromShopData(data: ShopDataEntity?): String? {


        return gson.toJson(data)
    }


    @TypeConverter
    fun toShopData(data: String?): ShopDataEntity? {


        return gson.fromJson(data, ShopDataEntity::class.java)
    }
}

class SkinConverter {

    val gson = Gson()

    @TypeConverter
    fun fromSkin(skins: MutableList<SkinEntity>): String {

        return gson.toJson(skins)
    }

    @TypeConverter
    fun toSkin(skins: String): MutableList<SkinEntity> {

        return gson.fromJson(skins, Array<SkinEntity>::class.java).toMutableList()
    }
}

class WeaponStatsConverter {

    val gson = Gson()

    @TypeConverter
    fun fromWeaponStats(stats: WeaponStatsEntity?): String? {


        return gson.toJson(stats)
    }

    @TypeConverter
    fun toWeaponStats(stat:String?):WeaponStatsEntity?{

        return gson.fromJson(stat,WeaponStatsEntity::class.java)
    }
}