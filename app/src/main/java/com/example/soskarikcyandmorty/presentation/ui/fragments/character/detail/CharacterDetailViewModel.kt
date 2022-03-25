package com.example.soskarikcyandmorty.presentation.ui.fragments.character.detail

import com.example.domain.usecase.CharacterDetailUseCase
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.CharacterModelUI
import com.example.soskarikcyandmorty.presentation.models.toCharacterUI
import com.example.soskarikcyandmorty.presentation.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase
) : BaseViewModel() {

    private val _fetchCharacterDetailId = MutableStateFlow<UIState<CharacterModelUI>>(UIState.Loading())
    val fetchCharacterDetailId: StateFlow<UIState<CharacterModelUI>> = _fetchCharacterDetailId

    fun fetchCharacterId(id: Int) {
        characterDetailUseCase(id).collectRequest(_fetchCharacterDetailId) { it.toCharacterUI() }
    }
}