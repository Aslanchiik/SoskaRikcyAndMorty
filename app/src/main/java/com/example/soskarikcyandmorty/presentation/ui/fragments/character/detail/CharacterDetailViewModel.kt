package com.example.soskarikcyandmorty.presentation.ui.fragments.character.detail

import com.example.domain.usecase.CharacterDetailUseCase
import com.example.soskarikcyandmorty.presentation.base.BaseViewModel
import com.example.soskarikcyandmorty.presentation.models.CharacterModelUI
import com.example.soskarikcyandmorty.presentation.models.toCharacterUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase
) : BaseViewModel() {

    private val _fetchCharacterDetailId = mutableUIStateFlow<CharacterModelUI>()
    val fetchCharacterDetailId = _fetchCharacterDetailId.asStateFlow()

    fun fetchCharacterId(id: Int) {
        characterDetailUseCase(id).collectRequest(_fetchCharacterDetailId) { it.toCharacterUI() }
    }
}