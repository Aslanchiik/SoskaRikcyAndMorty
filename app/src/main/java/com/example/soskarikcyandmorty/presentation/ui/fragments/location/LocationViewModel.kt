package com.example.soskarikcyandmorty.presentation.ui.fragments.location

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.repositories.LocationRepositoryImpl
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.LocationModelUI
import com.example.soskarikcyandmorty.presentation.models.toLocationUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repositoryImpl: LocationRepositoryImpl
) : BaseViewModel() {

    private val _locationState = MutableStateFlow<PagingData<LocationModelUI>?>(null)
    val locationState: StateFlow<PagingData<LocationModelUI>?> = _locationState

    fun fetchLocations(name: String?, type: String?, dimension: String?) =
        viewModelScope.launch {
            repositoryImpl.fetchLocation(name, type, dimension).collectLatest { it ->
                _locationState.value = it.map { it.toLocationUI() }
            }
        }

    private val _locationStateFilter = MutableStateFlow<PagingData<LocationModelUI>?>(null)
    val locationStateFilter: StateFlow<PagingData<LocationModelUI>?> = _locationStateFilter

    fun fetchLocationsFilter(name: String?, type: String?, dimension: String?) =
        viewModelScope.launch {
            repositoryImpl.fetchLocation(name, type, dimension).collectLatest { it ->
                _locationStateFilter.value = it.map { it.toLocationUI() }
            }
        }
}