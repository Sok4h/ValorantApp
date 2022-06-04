package com.sokah.valorantapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sokah.valorantapp.model.weapons.ShopData
import com.sokah.valorantapp.model.weapons.Skin
import com.sokah.valorantapp.model.weapons.WeaponStats

class ShopDataConverter {

    val gson = Gson()

    @TypeConverter
    fun fromShopData(data: ShopData?): String? {


        return gson.toJson(data)
    }


    @TypeConverter
    fun toShopData(data: String?): ShopData? {


        return gson.fromJson(data, ShopData::class.java)
    }
}

class SkinConverter {

    val gson = Gson()

    @TypeConverter
    fun fromSkin(skins: MutableList<Skin>): String {

        return gson.toJson(skins)
    }

    @TypeConverter
    fun toSkin(skins: String): MutableList<Skin> {

        return gson.fromJson(skins, Array<Skin>::class.java).toMutableList()
    }
}

class WeaponStatsConverter {

    val gson = Gson()

    @TypeConverter
    fun fromWeaponStats(stats: WeaponStats?): String? {


        return gson.toJson(stats)
    }

    @TypeConverter
    fun toWeaponStats(stat:String?):WeaponStats?{

        return gson.fromJson(stat,WeaponStats::class.java)
    }
}