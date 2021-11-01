package com.example.soskarikcyandmorty.ui.fragments.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soskarikcyandmorty.bases.BaseViewModel
import com.example.soskarikcyandmorty.domain.models.CharacterModel
import com.example.soskarikcyandmorty.domain.usecase.CharacterUseCase
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val useCase: CharacterUseCase,
) : BaseViewModel() {

    var page = 1
    private val _characterState = MutableLiveData<UIState<List<CharacterModel>>>()
    val characterState: LiveData<UIState<List<CharacterModel>>> = _characterState

    fun fetchCharacter(page : Int) {
        subscribeTo(_characterState) {
            useCase(page)
        }
    }
}