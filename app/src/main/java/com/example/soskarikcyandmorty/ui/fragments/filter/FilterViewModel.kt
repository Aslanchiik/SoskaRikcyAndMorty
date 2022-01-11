package com.example.soskarikcyandmorty.ui.fragments.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.domain.models.CharacterModel
import com.example.soskarikcyandmorty.domain.models.EpisodeModel
import com.example.soskarikcyandmorty.domain.models.LocationModel
import com.example.soskarikcyandmorty.domain.usecase.FilterCharacterUseCase
import com.example.soskarikcyandmorty.domain.usecase.FilterEpisodeUseCase
import com.example.soskarikcyandmorty.domain.usecase.FilterLocationUseCase
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val characterFilterUseCase: FilterCharacterUseCase,
    private val locationFilterUseCase: FilterLocationUseCase,
    private val episodeFilterUseCase: FilterEpisodeUseCase
) : BaseViewModel() {

    private val _fetchCharacterFilter = MutableLiveData<UIState<List<CharacterModel>>>()
    val fetchCharacterFilter: LiveData<UIState<List<CharacterModel>>> = _fetchCharacterFilter

    fun fetchCharacterFilter(name: String?) {
        subscribeTo(_fetchCharacterFilter) {
            characterFilterUseCase(name)
        }
    }

    private val _fetchLocationFilter = MutableLiveData<UIState<List<LocationModel>>>()
    val fetchLocationFilter: LiveData<UIState<List<LocationModel>>> = _fetchLocationFilter

    fun fetchLocationFilter(name: String?) {
        subscribeTo(_fetchLocationFilter) {
            locationFilterUseCase(name)
        }
    }

    private val _fetchEpisodeFilter = MutableLiveData<UIState<List<EpisodeModel>>>()
    val fetchEpisodeFilter: LiveData<UIState<List<EpisodeModel>>> = _fetchEpisodeFilter

    fun fetchEpisodeFilter(name: String?) {
        subscribeTo(_fetchEpisodeFilter) {
            episodeFilterUseCase(name)
        }
    }
}