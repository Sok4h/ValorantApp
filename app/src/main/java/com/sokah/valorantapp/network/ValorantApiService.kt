package com.sokah.valorantapp.network

import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.agents.AgentDto
import com.sokah.valorantapp.model.dtos.SkinDto
import com.sokah.valorantapp.model.weapons.WeaponDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class ValorantApiService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAgents(): BaseModel<MutableList<AgentDto>> {

        lateinit var languageCode :String

        languageCode = when (Locale.getDefault().language){

            "es"-> "es-ES"
            "fr"->"fr-FR"
            "id"->"id-ID"
            else -> "en-US"
        }

        //Log.e("idioma", languageCode )
        return withContext(Dispatchers.IO) {


            val response = retrofit.create(ValorantApi::class.java).getAgents(languageCode)

            response.body()!!
        }

    }


    suspend fun getWeapons():BaseModel<MutableList<WeaponDto>>{

        return withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getWeapons()
            response.body()!!
        }
    }

    suspend fun getSkins():BaseModel<MutableList<SkinDto>>{

        return withContext(Dispatchers.IO) {

             val response = retrofit.create(ValorantApi::class.java).getSkins()

            response.body()!!
        }
    }

    suspend fun getWeapon(weaponUuid:String):BaseModel<WeaponDto>{

        return withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getWeapon(weaponUuid)

            response.body()!!
        }
    }
}