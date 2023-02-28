package com.sokah.valorantapp.data.repository

import com.sokah.valorantapp.data.database.WeaponDao
import com.sokah.valorantapp.data.exceptions.CustomException
import com.sokah.valorantapp.data.exceptions.ErrorMessages
import com.sokah.valorantapp.data.model.entities.WeaponEntity
import com.sokah.valorantapp.data.model.toWeaponEntity
import com.sokah.valorantapp.data.network.ValorantApiService
import com.sokah.valorantapp.ui.mapper.uiMappers.toWeaponModel
import com.sokah.valorantapp.ui.mapper.uiModel.WeaponModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeaponRepository @Inject constructor(
    private val service: ValorantApiService,
    private val weaponDao: WeaponDao
) : IWeaponRepository {

    override suspend fun getAllWeapons(): Result<MutableList<WeaponModel>> {

        var databaseResult: MutableList<WeaponModel>
        var result: Result<MutableList<WeaponModel>>

        return withContext(Dispatchers.IO) {

            databaseResult = getAllWeaponsFromDatabase()
            try {

                val response = service.getWeapons()

                if (response.isSuccessful) {
                    val responseApi = response.body()
                    addWeapons(responseApi!!.data.map { it.toWeaponEntity() }.toMutableList())
                    result = Result.success(getAllWeaponsFromDatabase())

                } else if (databaseResult.isEmpty()) {
                    result =
                        Result.failure(
                            CustomException(
                                ErrorMessages.API_FAILED_AND_NO_CACHE.error
                            )
                        )
                } else {

                    result = Result.success(getAllWeaponsFromDatabase())
                }

            } catch (e: Exception) {

                if (databaseResult.isNotEmpty()) {

                    val filtered = getAllWeaponsFromDatabase().filterNot {

                        it.displayName.contains("Standar") || it.displayName.contentEquals("Melee")
                    }.toMutableList()
                    result = Result.success(filtered)
                } else {

                    result =
                        Result.failure(CustomException(ErrorMessages.NO_INTERNET_CONNECTION.error))
                }


            }
            return@withContext result


        }
    }

    override suspend fun addWeapons(weapons: MutableList<WeaponEntity>) {

        weaponDao.insertWeapons(weapons)

    }

    override suspend fun getWeaponByCategory(category: String): MutableList<WeaponModel> {

        return weaponDao.getWeaponByCategory("%$category%").map { it.toWeaponModel() }
            .toMutableList()
    }


    override suspend fun getAllWeaponsFromDatabase(): MutableList<WeaponModel> {

        return weaponDao.getAllWeapons().map { it.toWeaponModel() }.toMutableList()
    }
}