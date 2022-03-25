package com.example.soskarikcyandmorty.ui.fragments.character

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.bases.BaseViewModel
import com.example.data.repositories.CharacterRepositoryImpl
import com.example.domain.models.CharacterModel
import com.example.domain.usecase.EpisodeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepositoryImpl,
    private val episodeIdUseCase: EpisodeDetailUseCase
) : BaseViewModel() {

    fun fetchEpisode(id: Int) = episodeIdUseCase(id)

    private val _charactersState = MutableStateFlow<PagingData<CharacterModel>?>(null)
    val charactersState: StateFlow<PagingData<CharacterModel>?> = _charactersState

    fun fetchCharacters(name: String?, status: String?, gender: String?) {
        viewModelScope.launch {
            repository.fetchCharacter(name, status, gender).cachedIn(viewModelScope).collectLatest {
                _charactersState.value = it
            }
        }
    }

    private val _charactersStateFilter = MutableStateFlow<PagingData<CharacterModel>?>(null)
    val charactersStateFilter: StateFlow<PagingData<CharacterModel>?> = _charactersStateFilter

    fun fetchCharactersFilter(name: String?, status: String?, gender: String?) =
        viewModelScope.launch {
            repository.fetchCharacter(name, status, gender).cachedIn(viewModelScope).collectLatest {
                _charactersStateFilter.value = it
            }
        }
}