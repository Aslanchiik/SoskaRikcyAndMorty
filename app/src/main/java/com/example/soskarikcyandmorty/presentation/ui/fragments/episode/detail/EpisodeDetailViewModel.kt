package com.example.soskarikcyandmorty.presentation.ui.fragments.episode.detail

import com.example.domain.usecase.EpisodeDetailUseCase
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.EpisodeModelUI
import com.example.soskarikcyandmorty.presentation.models.toEpisodeUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val useEpisodeDetailCase: EpisodeDetailUseCase
) : BaseViewModel() {

    private val _fetchEpisodeId = mutableUIStateFlow<EpisodeModelUI>()
    val fetchEpisodeId = _fetchEpisodeId.asStateFlow()

    fun fetchCharacterId(id: Int) {
        useEpisodeDetailCase(id).collectRequest(_fetchEpisodeId) { it.toEpisodeUI() }
    }
}