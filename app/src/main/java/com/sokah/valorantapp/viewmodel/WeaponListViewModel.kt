package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.WeaponModel
import com.sokah.valorantapp.network.ValorantApiService
import kotlinx.coroutines.launch

class WeaponListViewModel : ViewModel() {

    private var service = ValorantApiService()

      val weaponList = MutableLiveData <BaseModel<MutableList<WeaponModel>>>()

    private lateinit var weapons : BaseModel<MutableList<WeaponModel>>

    init {

        viewModelScope.launch {

            val  response = service.getWeapons()
            weapons=response

            weaponList.postValue(response)
        }
    }
}