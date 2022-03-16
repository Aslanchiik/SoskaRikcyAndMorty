package com.example.soskarikcyandmorty.ui.fragments.character.detail

import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.domain.models.CharacterModel
import com.example.soskarikcyandmorty.domain.usecase.CharacterDetailUseCase
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase
) : BaseViewModel() {

    private val _fetchCharacterDetailId = MutableStateFlow<UIState<CharacterModel>>(UIState.Loading())
    val fetchCharacterDetailId: StateFlow<UIState<CharacterModel>> = _fetchCharacterDetailId

    fun fetchCharacterId(id: Int) {
        subscribeTo(_fetchCharacterDetailId) {
            characterDetailUseCase(id)
        }
    }
}