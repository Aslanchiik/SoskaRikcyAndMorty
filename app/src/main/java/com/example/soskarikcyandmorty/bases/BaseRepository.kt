package com.example.soskarikcyandmorty.bases

import androidx.lifecycle.liveData
import com.example.soskarikcyandmorty.common.Resource
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (ioException: Exception) {
            emit(
                Resource.Error(
                    data = null, message = ioException.localizedMessage ?: "error occupied"
                )
            )
        }
    }
}