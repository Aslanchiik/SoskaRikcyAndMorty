package com.example.soskarikcyandmorty.presentation.ui.fragments.filter

import com.example.domain.usecase.FilterCharacterUseCase
import com.example.domain.usecase.FilterEpisodeUseCase
import com.example.domain.usecase.FilterLocationUseCase
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val characterFilterUseCase: FilterCharacterUseCase,
    private val locationFilterUseCase: FilterLocationUseCase,
    private val episodeFilterUseCase: FilterEpisodeUseCase
) : BaseViewModel() {

    private val _fetchCharacterFilter = mutableUIStateFlow<List<CharacterModelUI>>()
    val fetchCharacterFilter = _fetchCharacterFilter.asStateFlow()

    fun fetchCharacterFilter(name: String?) {
        characterFilterUseCase(name).collectRequest(_fetchCharacterFilter) { it -> it.map { it.toCharacterUI() } }
    }

    private val _fetchLocationFilter = mutableUIStateFlow<List<LocationModelUI>>()
    val fetchLocationFilter = _fetchLocationFilter.asStateFlow()

    fun fetchLocationFilter(name: String?) {
        locationFilterUseCase(name).collectRequest(_fetchLocationFilter) { it -> it.map { it.toLocationUI() } }
    }

    private val _fetchEpisodeFilter = mutableUIStateFlow<List<EpisodeModelUI>>()
    val fetchEpisodeFilter = _fetchEpisodeFilter.asStateFlow()

    fun fetchEpisodeFilter(name: String?) {
        episodeFilterUseCase(name).collectRequest(_fetchEpisodeFilter) { it -> it.map { it.toEpisodeUI() } }
    }
}