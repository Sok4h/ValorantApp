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

class SkinsViewModel(application: Application) : AndroidViewModel(application) {

    val repository: SkinRepository = SkinRepository()
    val mutableSkinList = MutableLiveData<MutableList<Skin>>()
    lateinit var skins: MutableList<Skin>
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
            skins = response as MutableList<Skin>
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




