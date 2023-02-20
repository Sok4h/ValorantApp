package com.sokah.valorantapp.repository

import android.util.Log
import com.sokah.valorantapp.MyApplication
import com.sokah.valorantapp.db.ValorantDatabase
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.dataModel.SkinModel
import com.sokah.valorantapp.model.dtos.SkinDto
import com.sokah.valorantapp.model.dtos.toSkinEntity
import com.sokah.valorantapp.model.entities.SkinEntity
import com.sokah.valorantapp.model.entities.toSkinModel
import com.sokah.valorantapp.network.ValorantApiService
import retrofit2.HttpException
import java.io.IOException

class SkinRepository():ISkinRepository {

    private var service = ValorantApiService()
    private val database:ValorantDatabase  by lazy { MyApplication.getDatabase() }
    private val skinDao = database.skinDao()

    override suspend fun getAllSkins(): MutableList<SkinModel> {

        var resultApi: BaseModel<MutableList<SkinDto>>? = null


        try {

            resultApi = service.getSkins()
        } catch (e: IOException) {

            Log.e("TAG", e.message.toString())

        } catch (e: HttpException) {

            Log.e("TAG", e.message.toString())

        }

        if (resultApi != null) {

            if(resultApi.data.size > getAllSkinsdb().size){
                addSkins(resultApi.data.map { it.toSkinEntity() }.toMutableList())
            }

        }



        return getAllSkinsdb()
    }

     override suspend fun getAllSkinsdb(): MutableList<SkinModel> {

       return skinDao.getAllSkins().map { it.toSkinModel() }.toMutableList()
    }

     override suspend fun addSkins(data: MutableList<SkinEntity>) {

         skinDao.InsertSkins(data)
    }

    override suspend fun getSkinByUuid(uuid: String): SkinModel {

        return  skinDao.getSkinByUuid(uuid).toSkinModel()
    }

    override suspend fun  getSkinByType(type: String): MutableList<SkinModel>{


        return  skinDao.getSkinByType("%${type}%").map { it.toSkinModel() }.toMutableList()
    }


}