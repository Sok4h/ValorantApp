package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.weapons.Skin

import com.sokah.valorantapp.network.ValorantApiService
import kotlinx.coroutines.launch

class SkinsViewModel : ViewModel() {

    val service = ValorantApiService()

    val mutableSkinList = MutableLiveData<MutableList<Skin>>()
    lateinit var skins: MutableList<Skin>
    val isLoading = MutableLiveData<Boolean>()

    init {

        viewModelScope.launch {

            isLoading.postValue(true)

            val response = service.getSkins()

            response.data.filterNot {

                it.displayName.contains("Standar") || it.displayName.contentEquals("Melee")

            }.also {

                response.data = it.toMutableList()
                mutableSkinList.postValue(response.data!!)
                skins = response.data
                isLoading.postValue(false)
            }

        }

    }

    fun filterSkins(query: String) {

        if(query.isEmpty()){

            mutableSkinList.postValue(skins)

        }else{

            viewModelScope.launch {

                val response = service.getWeapons()

                response.data.filter {

                    it.displayName.contains(query)
                }.also {
                    response.data = it.toMutableList()
                    response.data.first().skins.filter {
                        !it.displayName.contains("Standar")

                    }.also {

                        val respons = it.toMutableList()
                        mutableSkinList.postValue(respons)
                    }

                }

            }

        }

    }

}


