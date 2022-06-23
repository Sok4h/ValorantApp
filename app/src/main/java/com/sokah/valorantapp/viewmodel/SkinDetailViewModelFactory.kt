package com.sokah.valorantapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sokah.valorantapp.model.weapons.WeaponModel

class SkinDetailViewModelFactory(private var skin :String,private var application:Application) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return SkinDetailViewModel(skin,application ) as T
    }


}