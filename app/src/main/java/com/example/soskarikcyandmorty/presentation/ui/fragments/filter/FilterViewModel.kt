package com.example.soskarikcyandmorty.presentation.ui.fragments.filter

import com.example.domain.usecase.FilterCharacterUseCase
import com.example.domain.usecase.FilterEpisodeUseCase
import com.example.domain.usecase.FilterLocationUseCase
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.*
import com.example.soskarikcyandmorty.presentation.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val characterFilterUseCase: FilterCharacterUseCase,
    private val locationFilterUseCase: FilterLocationUseCase,
    private val episodeFilterUseCase: FilterEpisodeUseCase
) : BaseViewModel() {

    private val _fetchCharacterFilter =
        MutableStateFlow<UIState<List<CharacterModelUI>>>(UIState.Loading())
    val fetchCharacterFilter: StateFlow<UIState<List<CharacterModelUI>>> = _fetchCharacterFilter

    fun fetchCharacterFilter(name: String?) {
        characterFilterUseCase(name).collectRequest(_fetchCharacterFilter) { it -> it.map { it.toCharacterUI() } }
    }

    private val _fetchLocationFilter =
        MutableStateFlow<UIState<List<LocationModelUI>>>(UIState.Loading())
    val fetchLocationFilter: StateFlow<UIState<List<LocationModelUI>>> = _fetchLocationFilter

    fun fetchLocationFilter(name: String?) {
        locationFilterUseCase(name).collectRequest(_fetchLocationFilter) { it -> it.map { it.toLocationUI() } }
    }

    private val _fetchEpisodeFilter =
        MutableStateFlow<UIState<List<EpisodeModelUI>>>(UIState.Loading())
    val fetchEpisodeFilter: StateFlow<UIState<List<EpisodeModelUI>>> = _fetchEpisodeFilter

    fun fetchEpisodeFilter(name: String?) {
        episodeFilterUseCase(name).collectRequest(_fetchEpisodeFilter) { it -> it.map { it.toEpisodeUI() } }
    }
}