package com.sokah.valorantapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel
import com.sokah.valorantapp.data.repository.SkinRepository
import kotlinx.coroutines.launch

class SkinDetailViewModel() :
    ViewModel() {

    val skinLive = MutableLiveData<SkinModel>()

    val mutableSkinList = MutableLiveData<MutableList<SkinModel>>()
    val isLoading = MutableLiveData<Boolean>()
    var skinObject: SkinModel? = null
    val repository: SkinRepository = SkinRepository()


    fun getSkin(uuid: String) {
        viewModelScope.launch {
            skinObject = repository.getSkinByUuid(uuid)
            skinLive.postValue(skinObject)
        }
    }


    fun getSkinsFiltered() {

        isLoading.postValue(true)
        viewModelScope.launch {
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

}