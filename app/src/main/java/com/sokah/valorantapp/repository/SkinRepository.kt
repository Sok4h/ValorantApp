package com.sokah.valorantapp.repository

import android.util.Log
import com.sokah.valorantapp.db.SkinDao
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.Skin
import com.sokah.valorantapp.network.ValorantApiService
import retrofit2.HttpException
import java.io.IOException

class SkinRepository(private val skinDAO: SkinDao) {

    private var service = ValorantApiService()

    suspend fun getAllSkins(): MutableList<Skin> {

        var resultApi: BaseModel<MutableList<Skin>>? = null


        try {

            resultApi = service.getSkins()
        } catch (e: IOException) {

            Log.e("TAG", e.message.toString())

        } catch (e: HttpException) {

            Log.e("TAG", e.message.toString())

        }

        if (resultApi != null) {

            if(resultApi.data.size > getAllSkinsdb().size){
                addSkins(resultApi.data)
            }

        }



        return getAllSkinsdb()
    }

     suspend fun getAllSkinsdb(): MutableList<Skin> {

       return skinDAO.getAllSkins()
    }

     suspend fun addSkins(data: MutableList<Skin>) {

        skinDAO.InsertSkins(data)
    }

    suspend fun getSkinByUuid(uuid: String): Skin{

        return  skinDAO.getSkinByUuid(uuid)
    }

    suspend fun  getSkinByType(type: String): MutableList<Skin>{

        return  skinDAO.getSkinByType(type)
    }


}