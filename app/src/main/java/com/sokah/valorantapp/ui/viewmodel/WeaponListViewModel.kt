package com.sokah.valorantapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.ui.mapper.uiModel.WeaponModel
import com.sokah.valorantapp.data.repository.IWeaponRepository
import com.sokah.valorantapp.data.repository.WeaponRepository
import kotlinx.coroutines.launch

class WeaponListViewModel() : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    val weaponList = MutableLiveData<MutableList<WeaponModel>>()
    val repository: IWeaponRepository

    init {

        repository = WeaponRepository()

        getWeapons()


    }

    fun getWeapons() {

        viewModelScope.launch {

            isLoading.postValue(true)
            val result = repository.getAllWeapons()
            isLoading.postValue(false)

            if (result != null) {
                weaponList.postValue(result!!)

            }
        }


    }

    fun sortWeapon(type: String) {

        viewModelScope.launch {

            val response = repository.getWeaponByCategory(type)

            weaponList.postValue(response!!)
        }


    }
}