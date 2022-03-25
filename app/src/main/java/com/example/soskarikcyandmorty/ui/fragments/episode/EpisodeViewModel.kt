package com.example.soskarikcyandmorty.ui.fragments.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.core.bases.BaseViewModel
import com.example.data.repositories.EpisodeRepositoryImpl
import com.example.domain.models.EpisodeModel
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

    private val _episodesState = MutableStateFlow<PagingData<EpisodeModel>>(PagingData.empty())
    val episodeState: StateFlow<PagingData<EpisodeModel>> = _episodesState

    fun fetchEpisodes(name: String?, episode: String?) = viewModelScope.launch {
        repositoryImpl.fetchEpisode(name, episode).collectLatest {
            _episodesState.value = it
        }
    }

    private val _episodesStateFilter =
        MutableStateFlow<PagingData<EpisodeModel>>(PagingData.empty())
    val episodeStateFilter: StateFlow<PagingData<EpisodeModel>> = _episodesStateFilter

    fun fetchEpisodesFilter(name: String?, episode: String?) = viewModelScope.launch {
        repositoryImpl.fetchEpisode(name, episode).collectLatest {
            _episodesStateFilter.value = it
        }
    }
}