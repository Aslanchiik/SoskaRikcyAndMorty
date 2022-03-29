package com.example.soskarikcyandmorty.presentation.ui.fragments.character.detail.episodecharacterdetail

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
class EpisodeCharacterDetailViewModel @Inject constructor(
    private val episodeRepositoryImpl: EpisodeRepositoryImpl
) : BaseViewModel() {

    private val _episodeCharacterDetailState = mutableStateWithPagingFlow<EpisodeModelUI>()
    val episodeCharacterDetailState = _episodeCharacterDetailState.asStateFlow()

    fun fetchEpisodes(name: String?, episode: String?) = viewModelScope.launch {
        episodeRepositoryImpl.fetchEpisode(name, episode).cachedIn(viewModelScope)
            .collectLatest { it ->
                _episodeCharacterDetailState.value = it.map { it.toEpisodeUI() }
            }
    }
}