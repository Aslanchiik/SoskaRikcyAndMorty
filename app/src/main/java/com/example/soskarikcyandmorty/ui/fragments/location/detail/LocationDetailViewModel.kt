package com.example.soskarikcyandmorty.ui.fragments.location.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.domain.models.LocationModel
import com.example.soskarikcyandmorty.domain.usecase.LocationDetailUseCase
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val useLocationDetailUseCase: LocationDetailUseCase
) : BaseViewModel() {

    private val _fetchLocationId = MutableLiveData<UIState<LocationModel>>()
    val fetchLocationId: LiveData<UIState<LocationModel>> = _fetchLocationId

    fun fetchCharacterId(id: Int) {
        subscribeTo(_fetchLocationId) {
            useLocationDetailUseCase(id)
        }
    }
}