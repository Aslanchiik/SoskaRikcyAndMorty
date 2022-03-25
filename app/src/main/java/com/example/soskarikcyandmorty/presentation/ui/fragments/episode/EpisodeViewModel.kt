package com.example.soskarikcyandmorty.presentation.ui.fragments.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.repositories.EpisodeRepositoryImpl
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.EpisodeModelUI
import com.example.soskarikcyandmorty.presentation.models.toEpisodeUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repositoryImpl: EpisodeRepositoryImpl
) : BaseViewModel() {

    private val _episodesState = MutableStateFlow<PagingData<EpisodeModelUI>?>(null)
    val episodeState: StateFlow<PagingData<EpisodeModelUI>?> = _episodesState

    fun fetchEpisodes(name: String?, episode: String?) = viewModelScope.launch {
        repositoryImpl.fetchEpisode(name, episode).collectLatest { it ->
            _episodesState.value = it.map { it.toEpisodeUI() }
        }
    }

    private val _episodesStateFilter = MutableStateFlow<PagingData<EpisodeModelUI>?>(null)
    val episodeStateFilter: StateFlow<PagingData<EpisodeModelUI>?> = _episodesStateFilter

    fun fetchEpisodesFilter(name: String?, episode: String?) = viewModelScope.launch {
        repositoryImpl.fetchEpisode(name, episode).collectLatest { it ->
            _episodesStateFilter.value = it.map { it.toEpisodeUI() }
        }
    }
}