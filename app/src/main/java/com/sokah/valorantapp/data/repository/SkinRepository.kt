package com.sokah.valorantapp.data.repository

import android.util.Log
import com.sokah.valorantapp.MyApplication
import com.sokah.valorantapp.data.database.ValorantDatabase
import com.sokah.valorantapp.data.model.BaseModel
import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel
import com.sokah.valorantapp.data.model.dtos.SkinDto
import com.sokah.valorantapp.data.model.entities.SkinEntity
import com.sokah.valorantapp.data.model.toSkinEntity
import com.sokah.valorantapp.data.network.ValorantApiService
import com.sokah.valorantapp.ui.mapper.uiMappers.toSkinModel
import retrofit2.HttpException
import java.io.IOException

class SkinRepository(): ISkinRepository {

    private var service = ValorantApiService()
    private val database: ValorantDatabase by lazy { MyApplication.getDatabase() }
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