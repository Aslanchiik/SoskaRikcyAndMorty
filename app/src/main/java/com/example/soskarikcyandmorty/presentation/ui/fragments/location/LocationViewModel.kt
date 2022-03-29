package com.example.soskarikcyandmorty.presentation.ui.fragments.location

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.data.repositories.LocationRepositoryImpl
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.LocationModelUI
import com.example.soskarikcyandmorty.presentation.models.toLocationUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationRepositoryImpl: LocationRepositoryImpl
) : BaseViewModel() {

    private val _locationState = mutableStateWithPagingFlow<LocationModelUI>()
    val locationState = _locationState.asStateFlow()

    fun fetchLocations(name: String?, type: String?, dimension: String?) = viewModelScope.launch {
        locationRepositoryImpl.fetchLocation(name, type, dimension).cachedIn(viewModelScope).collectLatest { it ->
            _locationState.value = it.map { it.toLocationUI() }
        }
    }

    private val _locationStateFilter = mutableStateWithPagingFlow<LocationModelUI>()
    val locationStateFilter = _locationStateFilter.asStateFlow()

    fun fetchLocationsFilter(name: String?, type: String?, dimension: String?) =
        viewModelScope.launch {
            locationRepositoryImpl.fetchLocation(name, type, dimension).cachedIn(viewModelScope)
                .collectLatest { it ->
                    _locationStateFilter.value = it.map { it.toLocationUI() }
                }
        }
}