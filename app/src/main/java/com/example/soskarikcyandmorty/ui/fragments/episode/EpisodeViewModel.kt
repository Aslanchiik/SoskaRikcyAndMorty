package com.example.soskarikcyandmorty.ui.fragments.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.domain.models.EpisodeModel
import com.example.soskarikcyandmorty.domain.usecase.EpisodeUseCase
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val useCase: EpisodeUseCase
) : BaseViewModel() {

    var page = 1
    private val _episodeState = MutableLiveData<UIState<List<EpisodeModel>>>()
    val episodeState: LiveData<UIState<List<EpisodeModel>>> = _episodeState

    fun fetchEpisode(page: Int) {
        subscribeTo(_episodeState) {
            useCase(page)
        }
    }
}