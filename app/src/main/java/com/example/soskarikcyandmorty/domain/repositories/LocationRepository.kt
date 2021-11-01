package com.example.soskarikcyandmorty.domain.repositories

import com.example.soskarikcyandmorty.common.Resource
import com.example.soskarikcyandmorty.domain.models.LocationModel
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun fetchLocation(page : Int) : Flow<Resource<List<LocationModel>>>
}