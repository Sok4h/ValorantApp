package com.sokah.valorantapp.data.repository

import com.sokah.valorantapp.data.database.SkinDao
import com.sokah.valorantapp.data.exceptions.CustomException
import com.sokah.valorantapp.data.exceptions.ErrorMessages
import com.sokah.valorantapp.data.model.entities.SkinEntity
import com.sokah.valorantapp.data.model.toSkinEntity
import com.sokah.valorantapp.data.network.ValorantApi
import com.sokah.valorantapp.ui.mapper.uiMappers.toSkinModel
import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel
import javax.inject.Inject

class SkinRepository @Inject constructor(
    private val service: ValorantApi,
    private val skinDao: SkinDao
) : ISkinRepository {

    override suspend fun getAllSkins(): Result<MutableList<SkinModel>> {

        val resultDatabase = getAllSkinsFromDatabase()
        var result: Result<MutableList<SkinModel>>

        try {
            val response = service.getSkins()

            if (response.isSuccessful) {

                val responseApi = response.body()
                addSkins(responseApi!!.data.map { it.toSkinEntity() }.toMutableList())
                result = Result.success(getAllSkinsFromDatabase())

            } else if (resultDatabase.isEmpty()) {
                result =
                    Result.failure(
                        CustomException(
                            ErrorMessages.API_FAILED_AND_NO_CACHE.error
                        )
                    )
            } else {

                result = Result.success(getAllSkinsFromDatabase())
            }

        } catch (e: Exception) {

            result = if (resultDatabase.isNotEmpty()) {

                Result.success(getAllSkinsFromDatabase())
            } else {

                Result.failure(CustomException(ErrorMessages.NO_INTERNET_CONNECTION.error))
            }

        }

        return result


    }

    override suspend fun getAllSkinsFromDatabase(): MutableList<SkinModel> {

        return skinDao.getAllSkins().map { it.toSkinModel() }.toMutableList()
    }

    override suspend fun addSkins(data: MutableList<SkinEntity>) {

        skinDao.InsertSkins(data)
    }

    override suspend fun getSkinByUuid(uuid: String): SkinModel {

        return skinDao.getSkinByUuid(uuid).toSkinModel()
    }

    override suspend fun getSkinByType(type: String): MutableList<SkinModel> {


        return skinDao.getSkinByType("%${type}%").map { it.toSkinModel() }.toMutableList()
    }


}