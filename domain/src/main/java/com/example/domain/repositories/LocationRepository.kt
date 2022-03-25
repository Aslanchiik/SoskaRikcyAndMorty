package com.example.domain.repositories

import com.example.common.resource.Resource
import com.example.domain.models.LocationModel
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun fetchLocationId(id: Int): Flow<Resource<LocationModel>>
}