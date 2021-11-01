package com.example.soskarikcyandmorty.ui.fragments.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.domain.models.LocationModel
import com.example.soskarikcyandmorty.domain.usecase.LocationUseCase
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val useCase: LocationUseCase
) : BaseViewModel() {

    var page = 1
    private val _locationState = MutableLiveData<UIState<List<LocationModel>>>()
    val locationState: LiveData<UIState<List<LocationModel>>> = _locationState

    fun fetchLocation(page: Int) {
        subscribeTo(_locationState) {
            useCase(page)
        }
    }
}