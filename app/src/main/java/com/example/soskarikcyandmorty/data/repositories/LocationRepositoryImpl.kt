package com.example.soskarikcyandmorty.data.repositories

import com.example.soskarikcyandmorty.bases.BaseRepository
import com.example.soskarikcyandmorty.data.network.apiservices.LocationApiService
import com.example.soskarikcyandmorty.data.network.dtos.toLocation
import com.example.soskarikcyandmorty.data.repositories.pagingsource.LocationPagingSource
import com.example.soskarikcyandmorty.domain.repositories.LocationRepository
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