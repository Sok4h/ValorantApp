package com.sokah.valorantapp.data.network

import com.sokah.valorantapp.data.model.BaseModel
import com.sokah.valorantapp.data.model.dtos.SkinDto
import com.sokah.valorantapp.model.agents.AgentDto
import com.sokah.valorantapp.model.weapons.WeaponDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.*

class ValorantApiService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAgents(): Response<BaseModel<MutableList<AgentDto>>>{

        lateinit var languageCode: String

        languageCode = when (Locale.getDefault().language) {

            "es" -> "es-ES"
            "fr" -> "fr-FR"
            "id" -> "id-ID"
            else -> "en-US"
        }
        return withContext(Dispatchers.IO) {


            val response = retrofit.create(ValorantApi::class.java).getAgents(languageCode)

            response
        }

    }


    suspend fun getWeapons(): Response<BaseModel<MutableList<WeaponDto>>> {

        return withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getWeapons()
            response
        }
    }

    suspend fun getSkins(): Response<BaseModel<MutableList<SkinDto>>> {

        return withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getSkins()

            response
        }
    }

    suspend fun getWeapon(weaponUuid: String): BaseModel<WeaponDto> {

        return withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getWeapon(weaponUuid)

            response.body()!!
        }
    }
}