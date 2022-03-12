package com.example.soskarikcyandmorty.ui.fragments.character.deatil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.domain.models.CharacterModel
import com.example.soskarikcyandmorty.domain.usecase.CharacterDetailUseCase
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase
) : BaseViewModel() {

    private val _fetchCharacterDetailId = MutableLiveData<UIState<CharacterModel>>()
    val fetchCharacterDetailId: LiveData<UIState<CharacterModel>> = _fetchCharacterDetailId

    fun fetchCharacterId(id: Int) {
        subscribeTo(_fetchCharacterDetailId) {
            characterDetailUseCase(id)
        }
    }
}