package com.example.data.repositories.pagingsource

import com.example.core.bases.BasePagingSource
import com.example.data.network.apiservices.LocationApiService
import com.example.data.network.dtos.LocationModelDto
import com.example.data.network.dtos.toLocation
import com.example.domain.models.LocationModel
import javax.inject.Inject

class LocationPagingSource @Inject constructor(
    private val service: LocationApiService,
    private val name: String?,
    private val type: String?,
    private val dimension: String?
) : BasePagingSource<LocationModelDto, LocationModel>(
    { service.fetchLocation(it, name, type, dimension) },
    { data -> data.map { it.toLocation() } }
)