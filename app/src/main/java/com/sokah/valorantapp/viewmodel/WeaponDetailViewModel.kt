package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sokah.valorantapp.model.weapons.WeaponModel

class WeaponDetailViewModel(weapon :String) :ViewModel() {


    val agentWeapon = MutableLiveData <WeaponModel>()

    init {

        var gson = Gson()
        val weaponObject =  gson.fromJson(weapon,WeaponModel::class.java)

        agentWeapon.postValue(weaponObject)
    }


}