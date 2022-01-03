package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sokah.valorantapp.model.weapons.WeaponModel

class WeaponDetailViewModelFactory(private var weapon :String) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return WeaponDetailViewModel(weapon ) as T
    }


}