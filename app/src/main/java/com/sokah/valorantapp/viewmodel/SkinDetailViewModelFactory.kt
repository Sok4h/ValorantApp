package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sokah.valorantapp.model.weapons.WeaponModel

class SkinDetailViewModelFactory(private var skin :String) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return SkinDetailViewModel(skin ) as T
    }


}