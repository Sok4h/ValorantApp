package com.sokah.valorantapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.data.repository.SkinRepository
import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel
import com.sokah.valorantapp.ui.viewStates.SkinViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkinsViewModel @Inject constructor(private val repository: SkinRepository) : ViewModel() {

    private val _viewState = MutableLiveData<SkinViewStates>()
    val viewState get() = _viewState

    init {

        viewModelScope.launch {
            getSkins()

        }

    }

    fun getSkins() {

        viewModelScope.launch {
            _viewState.postValue(SkinViewStates.Loading)

            val response = repository.getAllSkins()

            when {

                response.isSuccess -> _viewState.postValue(
                    SkinViewStates.Success(
                        filterDefaulSkins(
                            response.getOrDefault(
                                mutableListOf()
                            )
                        )
                    )
                )
                else -> {

                    _viewState.postValue(SkinViewStates.Error(response.exceptionOrNull() as Exception))
                }
            }
        }

    }

    fun filterSkinsByWeaponType(query: String) {
        viewModelScope.launch {
            val response = repository.getSkinByType(query)
            viewState.postValue(SkinViewStates.Success(filterDefaulSkins(response)))

        }
    }

    fun filterDefaulSkins(skins: MutableList<SkinModel>): MutableList<SkinModel> {

        skins.filterNot {
            it.displayName.contains("Standar") || it.displayName.contains("Random")


        }.toMutableList().also {
            return it
        }

    }
}








