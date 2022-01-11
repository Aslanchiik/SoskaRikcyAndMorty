package com.example.soskarikcyandmorty.data.repositories.pagingsource

import com.example.soskarikcyandmorty.bases.BasePagingSource
import com.example.soskarikcyandmorty.data.network.apiservices.LocationApiService
import com.example.soskarikcyandmorty.data.network.dtos.LocationModelDto
import com.example.soskarikcyandmorty.data.network.dtos.toLocation
import com.example.soskarikcyandmorty.domain.models.LocationModel
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