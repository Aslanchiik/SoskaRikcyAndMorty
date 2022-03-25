package com.example.soskarikcyandmorty.presentation.ui.fragments.character.detail.locationcharacterdetail

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
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
class LocationCharacterDetailViewModel @Inject constructor(
    private val locationRepositoryImpl: LocationRepositoryImpl
) : BaseViewModel() {

    private val _locationCharacterDetailState =
        MutableStateFlow<PagingData<LocationModelUI>>(PagingData.empty())
    val locationCharacterDetailState: StateFlow<PagingData<LocationModelUI>> =
        _locationCharacterDetailState

    fun fetchLocations(name: String?, type: String?, dimension: String?) {
        viewModelScope.launch {
            locationRepositoryImpl.fetchLocation(name, type, dimension).cachedIn(viewModelScope)
                .collectLatest { it ->
                    _locationCharacterDetailState.value = it.map { it.toLocationUI() }
                }
        }
    }
}