package com.sokah.valorantapp.data.repository

import com.sokah.valorantapp.MyApplication
import com.sokah.valorantapp.data.database.ValorantDatabase
import com.sokah.valorantapp.data.exceptions.CustomException
import com.sokah.valorantapp.data.model.entities.SkinEntity
import com.sokah.valorantapp.data.model.toSkinEntity
import com.sokah.valorantapp.data.network.ValorantApiService
import com.sokah.valorantapp.ui.mapper.uiMappers.toSkinModel
import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel

class SkinRepository : ISkinRepository {

    private var service = ValorantApiService()
    private val database: ValorantDatabase by lazy { MyApplication.getDatabase() }
    private val skinDao = database.skinDao()

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
                            "La conexión al api falló con codigo"
                                    + " ${response.code()} y la base de datos está vacía"
                        )
                    )
            } else {

                result = Result.success(getAllSkinsFromDatabase())
            }

        } catch (e: Exception) {

            result = if (resultDatabase.isNotEmpty()) {

                Result.success(getAllSkinsFromDatabase())
            } else {

                Result.failure(CustomException("No hay internet y la base de datos está vacia"))
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