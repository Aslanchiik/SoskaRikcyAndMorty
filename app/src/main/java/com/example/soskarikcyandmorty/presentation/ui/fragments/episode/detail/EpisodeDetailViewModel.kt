package com.example.soskarikcyandmorty.presentation.ui.fragments.episode.detail

import com.example.domain.usecase.EpisodeDetailUseCase
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.EpisodeModelUI
import com.example.soskarikcyandmorty.presentation.models.toEpisodeUI
import com.example.soskarikcyandmorty.presentation.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val useEpisodeDetailCase: EpisodeDetailUseCase
) : BaseViewModel() {

    private val _fetchEpisodeId = MutableStateFlow<UIState<EpisodeModelUI>>(UIState.Loading())
    val fetchEpisodeId: StateFlow<UIState<EpisodeModelUI>> = _fetchEpisodeId

    fun fetchCharacterId(id: Int) {
        useEpisodeDetailCase(id).collectRequest(_fetchEpisodeId) { it.toEpisodeUI() }
//        subscribeTo(_fetchEpisodeId) {
//            useEpisodeDetailCase(id)
//        }
    }
}