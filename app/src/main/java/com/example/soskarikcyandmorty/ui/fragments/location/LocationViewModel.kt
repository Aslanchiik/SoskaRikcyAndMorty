package com.example.soskarikcyandmorty.ui.fragments.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.data.repositories.LocationRepositoryImpl
import com.example.soskarikcyandmorty.domain.models.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repositoryImpl: LocationRepositoryImpl
) : BaseViewModel() {

    private val _locationState = MutableLiveData<PagingData<LocationModel>>()
    val locationState: LiveData<PagingData<LocationModel>> = _locationState

    fun fetchLocations(name: String?, type: String?, dimension: String?) =
        viewModelScope.launch {
            repositoryImpl.fetchLocation(name, type, dimension).collect {
                _locationState.value = it
            }
        }

    private val _locationStateFilter = MutableLiveData<PagingData<LocationModel>>()
    val locationStateFilter: LiveData<PagingData<LocationModel>> = _locationStateFilter

    fun fetchLocationsFilter(name: String?, type: String?, dimension: String?) =
        viewModelScope.launch {
            repositoryImpl.fetchLocation(name, type, dimension).collect {
                _locationStateFilter.value = it
            }
        }
}