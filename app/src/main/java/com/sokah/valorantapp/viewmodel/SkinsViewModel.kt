package com.sokah.valorantapp.viewmodel

import com.sokah.valorantapp.repository.ISkinRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.model.dataModel.SkinModel
import com.sokah.valorantapp.repository.SkinRepository
import kotlinx.coroutines.launch

class SkinsViewModel() : ViewModel() {

    val repository: ISkinRepository = SkinRepository()
    val mutableSkinList = MutableLiveData<MutableList<SkinModel>>()
    lateinit var skins: MutableList<SkinModel>
    val isLoading = MutableLiveData<Boolean>()

    init {

        viewModelScope.launch {
            getSkins()

        }

    }

    suspend fun getSkins() {

        isLoading.postValue(true)
        var response = repository.getAllSkins()

        response.filterNot {

            it.displayName.contains("Standar") || it.displayName.contentEquals("Melee")

        }.also {

            response = it.toMutableList()
            mutableSkinList.postValue(response)
            skins = response
            isLoading.postValue(false)
        }
    }

    fun filterSkins(query: String) {

        if (query.isEmpty()) {

            mutableSkinList.postValue(skins)

        } else {

            viewModelScope.launch {

                val response = repository.getSkinByType(query)

                response.filterNot {
                    it.displayName.contains("Standar")

                }.also {

                    val respons = it.toMutableList()
                    mutableSkinList.postValue(respons)
                }

            }

        }

    }

}




