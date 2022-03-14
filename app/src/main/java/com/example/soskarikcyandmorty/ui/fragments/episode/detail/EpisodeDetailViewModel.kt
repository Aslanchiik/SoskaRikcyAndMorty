package com.example.soskarikcyandmorty.ui.fragments.episode.detail

import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.domain.models.EpisodeModel
import com.example.soskarikcyandmorty.domain.usecase.EpisodeDetailUseCase
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val useEpisodeDetailCase: EpisodeDetailUseCase
) : BaseViewModel() {

    private val _fetchEpisodeId = MutableStateFlow<UIState<EpisodeModel>>(UIState.Loading())
    val fetchEpisodeId: StateFlow<UIState<EpisodeModel>> = _fetchEpisodeId

    fun fetchCharacterId(id: Int) {
        subscribeTo(_fetchEpisodeId) {
            useEpisodeDetailCase(id)
        }
    }
}