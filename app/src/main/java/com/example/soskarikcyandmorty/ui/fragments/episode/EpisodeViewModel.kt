package com.example.soskarikcyandmorty.ui.fragments.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.data.repositories.EpisodeRepositoryImpl
import com.example.soskarikcyandmorty.domain.models.EpisodeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repositoryImpl: EpisodeRepositoryImpl
) : BaseViewModel() {

    private val _episodesState = MutableLiveData<PagingData<EpisodeModel>>()
    val episodeState: LiveData<PagingData<EpisodeModel>> = _episodesState

    fun fetchEpisodes(name: String?, episode: String?) = viewModelScope.launch {
        repositoryImpl.fetchEpisode(name, episode).collect {
            _episodesState.value = it
        }
    }

    private val _episodesStateFilter = MutableLiveData<PagingData<EpisodeModel>>()
    val episodeStateFilter: LiveData<PagingData<EpisodeModel>> = _episodesStateFilter

    fun fetchEpisodesFilter(name: String?, episode: String?) = viewModelScope.launch {
        repositoryImpl.fetchEpisode(name, episode).collect {
            _episodesStateFilter.value = it
        }
    }
}