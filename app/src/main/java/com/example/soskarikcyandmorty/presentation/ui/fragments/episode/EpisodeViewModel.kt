package com.example.soskarikcyandmorty.presentation.ui.fragments.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.data.repositories.EpisodeRepositoryImpl
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.EpisodeModelUI
import com.example.soskarikcyandmorty.presentation.models.toEpisodeUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val episodeRepositoryImpl: EpisodeRepositoryImpl
) : BaseViewModel() {

    private val _episodesState = mutableStateWithPagingFlow<EpisodeModelUI>()
    val episodeState = _episodesState.asStateFlow()

    fun fetchEpisodes(name: String?, episode: String?) = viewModelScope.launch {
        episodeRepositoryImpl.fetchEpisode(name, episode).cachedIn(viewModelScope).collectLatest { it ->
            _episodesState.value = it.map { it.toEpisodeUI() }
        }
    }

    private val _episodesStateFilter = mutableStateWithPagingFlow<EpisodeModelUI>()
    val episodeStateFilter = _episodesStateFilter.asStateFlow()

    fun fetchEpisodeFilter(name: String?, episode: String?) = viewModelScope.launch {
        episodeRepositoryImpl.fetchEpisode(name, episode).cachedIn(viewModelScope)
            .collectLatest { it ->
                _episodesStateFilter.value = it.map { it.toEpisodeUI() }
            }
    }
}
