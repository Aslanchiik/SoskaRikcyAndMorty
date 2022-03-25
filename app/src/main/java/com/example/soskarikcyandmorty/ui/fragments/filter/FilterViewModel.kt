package com.example.soskarikcyandmorty.ui.fragments.filter

import com.example.common.state.UIState
import com.example.domain.models.CharacterModel
import com.example.domain.usecase.FilterCharacterUseCase
import com.example.domain.usecase.FilterEpisodeUseCase
import com.example.domain.usecase.FilterLocationUseCase
import com.example.core.bases.BaseViewModel
import com.example.domain.models.EpisodeModel
import com.example.domain.models.LocationModel
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

    private val _fetchCharacterFilter = MutableStateFlow<UIState<List<CharacterModel>>>(UIState.Loading())
    val fetchCharacterFilter: StateFlow<UIState<List<CharacterModel>>> = _fetchCharacterFilter

    fun fetchCharacterFilter(name: String?) {
        subscribeTo(_fetchCharacterFilter) {
            characterFilterUseCase(name)
        }
    }

    private val _fetchLocationFilter = MutableStateFlow<UIState<List<LocationModel>>>(UIState.Loading())
    val fetchLocationFilter: StateFlow<UIState<List<LocationModel>>> = _fetchLocationFilter

    fun fetchLocationFilter(name: String?) {
        subscribeTo(_fetchLocationFilter) {
            locationFilterUseCase(name)
        }
    }

    private val _fetchEpisodeFilter = MutableStateFlow<UIState<List<EpisodeModel>>>(UIState.Loading())
    val fetchEpisodeFilter: StateFlow<UIState<List<EpisodeModel>>> = _fetchEpisodeFilter

    fun fetchEpisodeFilter(name: String?) {
        subscribeTo(_fetchEpisodeFilter) {
            episodeFilterUseCase(name)
        }
    }
}