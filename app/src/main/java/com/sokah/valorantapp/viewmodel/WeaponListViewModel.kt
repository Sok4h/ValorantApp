package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.WeaponModel
import com.sokah.valorantapp.network.ValorantApiService
import kotlinx.coroutines.launch

class WeaponListViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    private var service = ValorantApiService()

      val weaponList = MutableLiveData <BaseModel<MutableList<WeaponModel>>>()

    private lateinit var weapons : BaseModel<MutableList<WeaponModel>>

    init {

        viewModelScope.launch {

            isLoading.postValue(true)
            val  response = service.getWeapons()
            isLoading.postValue(false)
            weapons=response

            weaponList.postValue(response)
        }
    }

    fun getWeapons(){

        weaponList.postValue(weapons)
    }
     fun sortWeapon(type:String){

        viewModelScope.launch {

            val response = service.getWeapons()

            response.data.filter {
                it.category.contains(type)
            }.also {

                response.data=it.toMutableList()
                weaponList.postValue(response)
            }

        }



    }
}