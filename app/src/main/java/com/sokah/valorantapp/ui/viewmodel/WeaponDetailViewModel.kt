package com.sokah.valorantapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sokah.valorantapp.ui.mapper.uiModel.WeaponModel

class WeaponDetailViewModel() :ViewModel() {


    val agentWeapon = MutableLiveData <WeaponModel>()

    fun getWeapon(weapon:String){

        var gson = Gson()
        val weaponObject =  gson.fromJson(weapon, WeaponModel::class.java)

        agentWeapon.postValue(weaponObject)
    }


}