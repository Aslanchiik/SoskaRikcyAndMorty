package com.example.soskarikcyandmorty.ui.fragments.location.detail

import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.domain.models.LocationModel
import com.example.soskarikcyandmorty.domain.usecase.LocationDetailUseCase
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val useLocationDetailUseCase: LocationDetailUseCase
) : BaseViewModel() {

    private val _fetchLocationId = MutableStateFlow<UIState<LocationModel>>(UIState.Loading())
    val fetchLocationId: StateFlow<UIState<LocationModel>> = _fetchLocationId

    fun fetchCharacterId(id: Int) {
        subscribeTo(_fetchLocationId) {
            useLocationDetailUseCase(id)
        }
    }
}