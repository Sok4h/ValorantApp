package com.sokah.valorantapp.network

import android.util.Log
import android.widget.Toast
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.Skin
import com.sokah.valorantapp.model.weapons.WeaponModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ValorantApiService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAgents(): BaseModel<MutableList<AgentModel>> {

        return withContext(Dispatchers.IO) {

            val response = retrofit.create(ValorantApi::class.java).getAgents()
            if(response.isSuccessful){

                Log.e("TAG", "todo bien", )
            }
            else{

                Log.e("TAG", response.message() )
            }

            response.body()!!
        }

    }

    suspend fun getAgent(agentUuid:String,language:String) :BaseModel<AgentModel>{

        Log.e("TAG", language )
        return  withContext(Dispatchers.IO) {


            val response = retrofit.create(ValorantApi::class.java).getAgent(agentUuid,language)

            response.body()!!

        }


    }

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