package com.sokah.valorantapp.network

import android.util.Log
import android.widget.Toast
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.Skin
import com.sokah.valorantapp.model.weapons.WeaponModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class ValorantApiService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAgents(): BaseModel<MutableList<AgentModel>> {

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

/*
    suspend fun getAgent(agentUuid:String,language:String) :AgentModel{

        return  withContext(Dispatchers.IO) {


            val response = retrofit.create(ValorantApi::class.java).getAgent(agentUuid,language)

            response.body()?.data!!

        }


    }
*/

    suspend fun getWeapons():BaseModel<MutableList<WeaponModel>>{

        return withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getWeapons()
            response.body()!!
        }
    }

    suspend fun getSkins():BaseModel<MutableList<Skin>>{

        return withContext(Dispatchers.IO) {

             val response = retrofit.create(ValorantApi::class.java).getSkins()

            response.body()!!
        }
    }

    suspend fun getWeapon(weaponUuid:String):BaseModel<WeaponModel>{

        return withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getWeapon(weaponUuid)

            response.body()!!
        }
    }
}