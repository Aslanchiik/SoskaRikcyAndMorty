package com.example.soskarikcyandmorty.ui.fragments.character

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.data.repositories.CharacterRepositoryImpl
import com.example.soskarikcyandmorty.domain.models.CharacterModel
import com.example.soskarikcyandmorty.domain.usecase.EpisodeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepositoryImpl,
    private val episodeIdUseCase: EpisodeDetailUseCase
) : BaseViewModel() {

    fun fetchEpisode(id: Int) = episodeIdUseCase(id)

    private val _charactersState = MutableStateFlow<PagingData<CharacterModel>>(PagingData.empty())
    val charactersState: StateFlow<PagingData<CharacterModel>> = _charactersState

    fun fetchCharacters(name: String?, status: String?, gender: String?) {
        viewModelScope.launch {
            repository.fetchCharacter(name, status, gender).collect {
                _charactersState.value = it
            }
        }
    }

    private val _charactersStateFilter = MutableStateFlow<PagingData<CharacterModel>>(PagingData.empty())
    val charactersStateFilter: StateFlow<PagingData<CharacterModel>> = _charactersStateFilter

    fun fetchCharactersFilter(name: String?, status: String?, gender: String?) =
        viewModelScope.launch {
            repository.fetchCharacter(name, status, gender).collect {
                _charactersStateFilter.value = it
            }
        }
}