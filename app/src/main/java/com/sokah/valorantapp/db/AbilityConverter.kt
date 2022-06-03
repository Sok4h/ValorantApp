package com.sokah.valorantapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sokah.valorantapp.model.agents.Ability
import com.sokah.valorantapp.model.agents.Role

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