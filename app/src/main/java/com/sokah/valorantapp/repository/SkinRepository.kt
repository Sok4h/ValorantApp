package com.sokah.valorantapp.repository

import android.util.Log
import com.sokah.valorantapp.MyApplication
import com.sokah.valorantapp.db.SkinDao
import com.sokah.valorantapp.db.ValorantDatabase
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.Skin
import com.sokah.valorantapp.network.ValorantApiService
import retrofit2.HttpException
import java.io.IOException

class SkinRepository():ISkinRepository {

    private var service = ValorantApiService()
    private val database:ValorantDatabase  by lazy { MyApplication.getDatabase() }
    private val skinDao = database.skinDao()

    override suspend fun getAllSkins(): MutableList<Skin> {

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

     override suspend fun getAllSkinsdb(): MutableList<Skin> {

       return skinDao.getAllSkins()
    }

     override suspend fun addSkins(data: MutableList<Skin>) {

         skinDao.InsertSkins(data)
    }

    override suspend fun getSkinByUuid(uuid: String): Skin{

        return  skinDao.getSkinByUuid(uuid)
    }

    override suspend fun  getSkinByType(type: String): MutableList<Skin>{


        return  skinDao.getSkinByType("%${type}%")
    }


}