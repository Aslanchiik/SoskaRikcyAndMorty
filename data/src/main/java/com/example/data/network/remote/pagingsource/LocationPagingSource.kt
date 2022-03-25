package com.example.data.network.remote.pagingsource

import com.example.data.network.remote.apiservices.LocationApiService
import com.example.data.network.remote.dtos.LocationModelDto
import com.example.data.network.remote.dtos.toLocation
import com.example.data.network.remote.pagingsource.base.BasePagingSource
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