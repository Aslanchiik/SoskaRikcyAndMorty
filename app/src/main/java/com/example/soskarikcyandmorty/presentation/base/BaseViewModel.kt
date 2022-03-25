package com.example.soskarikcyandmorty.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Resource
import com.example.soskarikcyandmorty.presentation.ui.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected open fun <T, S> Flow<Resource<T>>.collectRequest(
        state: MutableStateFlow<UIState<S>>,
        mappedData: (T) -> S
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@collectRequest.collect {
                when (it) {
                    is Resource.Error -> it.message?.let { error ->
                        state.value = UIState.Error(error)
                    }
                    is Resource.Success -> it.data?.let { data ->
                        state.value = UIState.Success(mappedData(data))
                    }
                }
            }
        }
    }

//    protected fun <T> subscribeTo(
//        state: MutableStateFlow<UIState<T>>,
//        request: () -> Flow<Resource<T>>
//    ) {
//        viewModelScope.launch {
//            request().collect {
//                when (it) {
//                    is Resource.Loading -> {
//                        state.value = UIState.Loading()
//                    }
//                    is Resource.Error -> {
//                        it.message?.let { error ->
//                            state.value = UIState.Error(error)
//                        }
//                    }
//                    is Resource.Success -> {
//                        it.data?.let { data ->
//                            state.value = UIState.Success(data)
//
//                        }
//                    }
//                }
//            }
//        }
//    }
}