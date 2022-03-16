package com.example.soskarikcyandmorty.ui.fragments.character.detail.episodecharacterdetail

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.data.repositories.EpisodeRepositoryImpl
import com.example.soskarikcyandmorty.domain.models.EpisodeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeCharacterDetailViewModel @Inject constructor(
    private val episodeRepositoryImpl: EpisodeRepositoryImpl
) : BaseViewModel() {

    private val _episodeCharacterDetailState = MutableStateFlow<PagingData<EpisodeModel>>(PagingData.empty())
    val episodeCharacterDetailState: StateFlow<PagingData<EpisodeModel>> = _episodeCharacterDetailState

    fun fetchEpisodes(name: String?, episode: String?) = viewModelScope.launch {
        episodeRepositoryImpl.fetchEpisode(name, episode).collect {
            _episodeCharacterDetailState.value = it
        }
    }
}