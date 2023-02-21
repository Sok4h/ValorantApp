package com.sokah.valorantapp.data.repository

import android.util.Log
import com.sokah.valorantapp.MyApplication
import com.sokah.valorantapp.data.database.ValorantDatabase
import com.sokah.valorantapp.data.database.WeaponDao
import com.sokah.valorantapp.data.model.BaseModel
import com.sokah.valorantapp.ui.mapper.uiModel.WeaponModel
import com.sokah.valorantapp.data.model.entities.WeaponEntity
import com.sokah.valorantapp.data.model.toWeaponEntity
import com.sokah.valorantapp.model.weapons.WeaponDto
import com.sokah.valorantapp.data.network.ValorantApiService
import com.sokah.valorantapp.ui.mapper.uiMappers.toWeaponModel
import retrofit2.HttpException
import java.io.IOException

class WeaponRepository() : IWeaponRepository {


    private val database: ValorantDatabase by lazy { MyApplication.getDatabase() }
    private val weaponDao: WeaponDao = database.weaponDao()
    private var service = ValorantApiService()

    var resultApi: BaseModel<MutableList<WeaponDto>>? = null


    override suspend fun getAllWeapons(): MutableList<WeaponModel>? {


        try {

            resultApi = service.getWeapons()
        } catch (e: IOException) {

            Log.e("TAG", e.message.toString())

        } catch (e: HttpException) {

            Log.e("TAG", e.message.toString())

        }

        if (resultApi != null) {

            addWeapons(resultApi!!.data.map { it.toWeaponEntity() }.toMutableList())
        }

        return getAllWeaponsdb()
    }

    override suspend fun addWeapons(weapons: MutableList<WeaponEntity>) {

        weaponDao.insertWeapons(weapons)

    }

    override suspend fun getWeaponByCategory(category: String): MutableList<WeaponModel>? {


        return weaponDao.getWeaponByCategory("%$category%")?.map { it.toWeaponModel() }
            ?.toMutableList()
    }


    override suspend fun getAllWeaponsdb(): MutableList<WeaponModel>? {

        return weaponDao.getAllWeapons()?.map { it.toWeaponModel() }?.toMutableList()
    }
}