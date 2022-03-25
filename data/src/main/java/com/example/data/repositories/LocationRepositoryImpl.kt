package com.example.data.repositories

import com.example.core.bases.BaseRepository
import com.example.data.repositories.pagingsource.LocationPagingSource
import com.example.data.network.apiservices.LocationApiService
import com.example.data.network.dtos.toLocation
import com.example.domain.repositories.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val service: LocationApiService
) : BaseRepository(), LocationRepository {

    fun fetchLocation(name: String?, type: String?, dimension: String?) =
        doPagingRequest(LocationPagingSource(service, name, type, dimension))

    override fun fetchLocationId(id: Int) = doRequest {
        service.fetchLocationId(id).toLocation()
    }
}