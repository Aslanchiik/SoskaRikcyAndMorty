package com.example.soskarikcyandmorty.ui.fragments.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.data.repositories.CharacterRepositoryImpl
import com.example.soskarikcyandmorty.domain.models.CharacterModel
import com.example.soskarikcyandmorty.domain.usecase.CharacterDetailUseCase
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepositoryImpl,
    private val useDetailUseCase: CharacterDetailUseCase,
) : BaseViewModel() {

    private val _charactersState = MutableLiveData<PagingData<CharacterModel>>()
    val charactersState: LiveData<PagingData<CharacterModel>> = _charactersState

    fun fetchCharacters(name: String?, status: String?, gender: String?) {
        viewModelScope.launch {
            repository.fetchCharacter(name, status, gender).collect {
                _charactersState.value = it
            }
        }
    }

    private val _charactersStateFilter = MutableLiveData<PagingData<CharacterModel>>()
    val charactersStateFilter: LiveData<PagingData<CharacterModel>> = _charactersStateFilter

    fun fetchCharactersFilter(name: String?, status: String?, gender: String?) =
        viewModelScope.launch {
            repository.fetchCharacter(name, status, gender).collect {
                _charactersStateFilter.value = it
            }
        }

    private val _fetchCharacterId = MutableLiveData<UIState<CharacterModel>>()
    val fetchCharacterId: LiveData<UIState<CharacterModel>> = _fetchCharacterId

    fun fetchCharacterId(id: Int) {
        subscribeTo(_fetchCharacterId) {
            useDetailUseCase(id)
        }
    }
}