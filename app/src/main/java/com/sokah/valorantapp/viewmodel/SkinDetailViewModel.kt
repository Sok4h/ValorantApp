package com.sokah.valorantapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.db.ValorantDatabase
import com.sokah.valorantapp.model.weapons.Skin
import com.sokah.valorantapp.network.ValorantApiService
import com.sokah.valorantapp.repository.SkinRepository
import kotlinx.coroutines.launch

class SkinDetailViewModel(var uuid: String, application: Application) :
    AndroidViewModel(application) {

    val skinLive = MutableLiveData<Skin>()

    val mutableSkinList = MutableLiveData<MutableList<Skin>>()
    val isLoading = MutableLiveData<Boolean>()
     var skinObject :Skin ?=null
    val repository: SkinRepository

    init {

        val skinDao = ValorantDatabase.getInstance(application).skinDao()
        repository = SkinRepository(skinDao)
        /* var gson = Gson()
         skinObject = gson.fromJson(skin, Skin::class.java)*/



        viewModelScope.launch {
            getSkin()
            getSkinsFiltered()

        }

    }

    suspend fun getSkin() {
        skinObject = repository.getSkinByUuid(uuid)
        skinLive.postValue(skinObject)
    }


    suspend fun getSkinsFiltered() {

        isLoading.postValue(true)

        var response = repository.getAllSkinsdb()

        response.filterNot {

            it.displayName.contains("Standar") || it.displayName.contentEquals("Melee")

        }.also {

            it.filter {

                it.themeUuid.contentEquals(skinObject?.themeUuid)
            }.also {

                response = it.toMutableList()
                mutableSkinList.postValue(response)
                isLoading.postValue(false)
            }


        }

    }

}