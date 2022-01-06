package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sokah.valorantapp.model.weapons.Skin

class SkinDetailViewModel(skin:String) : ViewModel() {

    val skinLive = MutableLiveData <Skin>()

    init {

        var gson = Gson()
        val skinObject =  gson.fromJson(skin, Skin::class.java)

        skinLive.postValue(skinObject)
    }
}