package com.example.soskarikcyandmorty.ui.fragments.character.detail.episodecharacterdetail

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
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
class EpisodeCharacterDetailViewModel @Inject constructor(
    private val episodeRepositoryImpl: EpisodeRepositoryImpl
) : BaseViewModel() {

    private val _episodeCharacterDetailState =
        MutableStateFlow<PagingData<EpisodeModel>>(PagingData.empty())
    val episodeCharacterDetailState: StateFlow<PagingData<EpisodeModel>> =
        _episodeCharacterDetailState

    fun fetchEpisodes(name: String?, episode: String?) {
        viewModelScope.launch {
            episodeRepositoryImpl.fetchEpisode(name, episode).cachedIn(viewModelScope)
                .collectLatest {
                    _episodeCharacterDetailState.value = it
                }
        }
    }
}