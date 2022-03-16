package com.example.soskarikcyandmorty.ui.fragments.character.detail.locationcharacterdetail

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.data.repositories.LocationRepositoryImpl
import com.example.soskarikcyandmorty.domain.models.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationCharacterDetailViewModel @Inject constructor(
    private val locationRepositoryImpl: LocationRepositoryImpl
) : BaseViewModel() {

    private val _locationCharacterDetailState = MutableStateFlow<PagingData<LocationModel>>(PagingData.empty())
    val locationCharacterDetailState: StateFlow<PagingData<LocationModel>> = _locationCharacterDetailState

    fun fetchLocations(name: String?, type: String?, dimension: String?) {
        viewModelScope.launch {
            locationRepositoryImpl.fetchLocation(name, type, dimension).cachedIn(viewModelScope)
                .collect {
                    _locationCharacterDetailState.value = it
                }
        }
    }
}