package com.sokah.valorantapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sokah.valorantapp.model.agents.Ability
import com.sokah.valorantapp.model.agents.Role
import com.sokah.valorantapp.model.weapons.Chroma
import com.sokah.valorantapp.model.weapons.Level

class AbilityConverter {

    val gson = Gson()

    @TypeConverter

    fun fromAbility(abilities: MutableList<Ability>): String {


        return gson.toJson(abilities)
    }

    @TypeConverter
    fun toAbilities(abilites: String): MutableList<Ability> {


        return gson.fromJson(abilites, Array<Ability>::class.java).toMutableList()

    }
}

class RoleConverter {


    val gson = Gson()

    @TypeConverter

    fun fromRole(role: Role): String {
        return gson.toJson(role)
    }

    @TypeConverter
    fun toRole(role: String): Role {


        return gson.fromJson(role, Role::class.java)

    }

}

class LevelConverter{

    val gson = Gson()
    @TypeConverter

    fun fromLevel(level:List<Level>):String{

        return  gson.toJson(level)
    }
    @TypeConverter
    fun toLevel(level:String):List<Level>{

        return  gson.fromJson(level,Array<Level>::class.java).toList()
    }

}

class  ChromaConverter{

    val gson = Gson()
    @TypeConverter
    fun fromChroma(chroma:List<Chroma>):String{

        return gson.toJson(chroma)
    }
    @TypeConverter

    fun toChroma(chroma:String):List<Chroma>{

        return gson.fromJson(chroma,Array<Chroma>::class.java).toList()
    }
}