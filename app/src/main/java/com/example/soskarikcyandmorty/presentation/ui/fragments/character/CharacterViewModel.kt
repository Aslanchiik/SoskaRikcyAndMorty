package com.example.soskarikcyandmorty.presentation.ui.fragments.character

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.data.repositories.CharacterRepositoryImpl
import com.example.domain.usecase.EpisodeDetailUseCase
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.CharacterModelUI
import com.example.soskarikcyandmorty.presentation.models.toCharacterUI
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

    private val _charactersState = MutableStateFlow<PagingData<CharacterModelUI>?>(null)
    val charactersState: StateFlow<PagingData<CharacterModelUI>?> = _charactersState

    fun fetchCharacters(name: String?, status: String?, gender: String?) {
        viewModelScope.launch {
            repository.fetchCharacter(name, status, gender).cachedIn(viewModelScope)
                .collectLatest { it ->
                    _charactersState.value = it.map { it.toCharacterUI() }
                }
        }
    }

    private val _charactersStateFilter = MutableStateFlow<PagingData<CharacterModelUI>?>(null)
    val charactersStateFilter: StateFlow<PagingData<CharacterModelUI>?> = _charactersStateFilter

    fun fetchCharactersFilter(name: String?, status: String?, gender: String?) =
        viewModelScope.launch {
            repository.fetchCharacter(name, status, gender).cachedIn(viewModelScope)
                .collectLatest { it ->
                    _charactersStateFilter.value = it.map { it.toCharacterUI() }
                }
        }
}