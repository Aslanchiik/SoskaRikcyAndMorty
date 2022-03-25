package com.example.soskarikcyandmorty.ui.fragments.location.detail

import com.example.core.bases.BaseViewModel
import com.example.common.state.UIState
import com.example.domain.models.LocationModel
import com.example.domain.usecase.LocationDetailUseCase
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