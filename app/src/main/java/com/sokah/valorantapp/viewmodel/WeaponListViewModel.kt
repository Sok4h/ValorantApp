package com.sokah.valorantapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.db.ValorantDatabase
import com.sokah.valorantapp.model.weapons.WeaponModel
import com.sokah.valorantapp.repository.WeaponRepository
import kotlinx.coroutines.launch

class WeaponListViewModel(application: Application) : AndroidViewModel(application) {

    val isLoading = MutableLiveData<Boolean>()

    val weaponList = MutableLiveData<MutableList<WeaponModel>>()
    val repository: WeaponRepository

    init {

        val weaponDao = ValorantDatabase.getInstance(application).weaponDao()
        repository = WeaponRepository(weaponDao)

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

            weaponList.postValue(response)
        }


    }
}