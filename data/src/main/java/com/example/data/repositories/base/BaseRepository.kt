package com.example.data.repositories.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.network.remote.pagingsource.base.BasePagingSource
import com.example.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    protected fun <T> doRequest(
        doSomethingInSuccess: ((T) -> Unit)? = null,
        request: suspend () -> T
    ) = flow<Resource<T>> {
        val requestProperty = request()
        emit(Resource.Success(data = requestProperty))
        doSomethingInSuccess?.invoke(requestProperty)
    }.flowOn(Dispatchers.IO).catch { exception ->
        emit(Resource.Error(message = exception.localizedMessage ?: "Error Occurred!"))
    }

//    protected fun <T> doRequest(request: suspend () -> T) = flow {
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success(data = request()))
//        } catch (ioException: Exception) {
//            emit(
//                Resource.Error(
//                    data = null, message = ioException.localizedMessage ?: "error occupied"
//                )
//            )
//        }
//    }

    protected fun <ValueDto : Any, Value : Any> doPagingRequest(
        pagingSource: BasePagingSource<ValueDto, Value>,
        pageSize: Int = 10,
        prefetchDistance: Int = pageSize,
        enabledPlaceholder: Boolean = true,
        initialLoadSize: Int = pageSize * 3,
        maxSize: Int = Int.MAX_VALUE,
        jumpThreshold: Int = Int.MIN_VALUE,
    ): Flow<PagingData<Value>> {
        return Pager(
            config = PagingConfig(
                pageSize,
                prefetchDistance,
                enabledPlaceholder,
                initialLoadSize,
                maxSize,
                jumpThreshold
            ),
            pagingSourceFactory = { pagingSource }
        ).flow
    }
}