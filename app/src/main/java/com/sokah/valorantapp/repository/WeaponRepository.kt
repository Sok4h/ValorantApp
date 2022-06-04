package com.sokah.valorantapp.repository

import android.util.Log
import com.sokah.valorantapp.db.AgentDao
import com.sokah.valorantapp.db.WeaponDao
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.WeaponModel
import com.sokah.valorantapp.network.ValorantApiService
import retrofit2.HttpException
import java.io.IOException

class WeaponRepository(private val weaponDao: WeaponDao) {


    private var service = ValorantApiService()

    var resultApi: BaseModel<MutableList<WeaponModel>>? = null


    suspend fun getAllWeapons(): MutableList<WeaponModel>? {


        try {

            resultApi = service.getWeapons()
        } catch (e: IOException) {

            Log.e("TAG", e.message.toString())

        } catch (e: HttpException) {

            Log.e("TAG", e.message.toString())

        }

        if (resultApi != null) {

            addWeapons(resultApi!!.data)
        }

        return getAllWeaponsdb()
    }

    suspend fun addWeapons(weapons: MutableList<WeaponModel>) {

        weaponDao.insertWeapons(weapons)

    }

    suspend fun getWeaponByCategory(category: String): MutableList<WeaponModel>? {


        return weaponDao.getWeaponByCategory("%$category%")
    }


    suspend fun getAllWeaponsdb(): MutableList<WeaponModel>? {

        return weaponDao.getAllWeapons()
    }
}