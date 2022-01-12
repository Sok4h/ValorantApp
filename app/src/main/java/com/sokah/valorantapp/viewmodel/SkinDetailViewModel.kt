package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.Skin
import com.sokah.valorantapp.network.ValorantApiService
import kotlinx.coroutines.launch

class SkinDetailViewModel(skin: String) : ViewModel() {

    val skinLive = MutableLiveData<Skin>()

    val mutableSkinList = MutableLiveData<MutableList<Skin>>()
    val service = ValorantApiService()
    val isLoading = MutableLiveData<Boolean>()
    var skinObject: Skin

    init {

        var gson = Gson()
        skinObject = gson.fromJson(skin, Skin::class.java)

        skinLive.postValue(skinObject)

        viewModelScope.launch { getSkinsFiltered() }

    }


    suspend fun getSkinsFiltered() {

        isLoading.postValue(true)

        val response = service.getSkins()

        response.data.filterNot {

            it.displayName.contains("Standar") || it.displayName.contentEquals("Melee")

        }.also {

            it.filter {

                it.themeUuid.contentEquals(skinObject.themeUuid)
            }.also {

                response.data = it.toMutableList()
                mutableSkinList.postValue(response.data!!)
                isLoading.postValue(false)
            }


        }

    }

}