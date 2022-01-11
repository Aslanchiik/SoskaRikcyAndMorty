package com.example.soskarikcyandmorty.ui.fragments.episode.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.domain.models.EpisodeModel
import com.example.soskarikcyandmorty.domain.usecase.EpisodeDetailUseCase
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val useEpisodeDetailCase: EpisodeDetailUseCase
) : BaseViewModel() {

    private val _fetchEpisodeId = MutableLiveData<UIState<EpisodeModel>>()
    val fetchEpisodeId: LiveData<UIState<EpisodeModel>> = _fetchEpisodeId

    fun fetchCharacterId(id: Int) {
        subscribeTo(_fetchEpisodeId) {
            useEpisodeDetailCase(id)
        }
    }
}