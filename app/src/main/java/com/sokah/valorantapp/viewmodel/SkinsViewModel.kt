package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.Skin

import com.sokah.valorantapp.network.ValorantApiService
import kotlinx.coroutines.launch

class SkinsViewModel : ViewModel() {

    val service = ValorantApiService()

    val mutableSkinList = MutableLiveData <BaseModel<MutableList<Skin>>>()
 /*   lateinit var skins : BaseModel<MutableList<Skin>>*/
    val isLoading = MutableLiveData<Boolean>()

    init{

        viewModelScope.launch {

            isLoading.postValue(true)

            val response = service.getSkins()

            response.data.filterNot{

                it.displayName.contains("Standar")|| it.displayName.contentEquals("Melee")

            }.also {

                response.data=it.toMutableList()
                mutableSkinList.postValue(response)

                isLoading.postValue(false)
            }

        }

    }
}