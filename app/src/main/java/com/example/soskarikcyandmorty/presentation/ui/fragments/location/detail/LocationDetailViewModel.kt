package com.example.soskarikcyandmorty.presentation.ui.fragments.location.detail

import com.example.domain.usecase.LocationDetailUseCase
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.LocationModelUI
import com.example.soskarikcyandmorty.presentation.models.toLocationUI
import com.example.soskarikcyandmorty.presentation.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val useLocationDetailUseCase: LocationDetailUseCase
) : BaseViewModel() {

    private val _fetchLocationId = MutableStateFlow<UIState<LocationModelUI>>(UIState.Loading())
    val fetchLocationId: StateFlow<UIState<LocationModelUI>> = _fetchLocationId

    fun fetchCharacterId(id: Int) {
        useLocationDetailUseCase(id).collectRequest(_fetchLocationId){it.toLocationUI()}
    }
}