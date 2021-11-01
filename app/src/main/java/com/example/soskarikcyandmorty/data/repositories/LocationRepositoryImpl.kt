package com.example.soskarikcyandmorty.data.repositories

import com.example.soskarikcyandmorty.bases.BaseRepository
import com.example.soskarikcyandmorty.data.network.apiservices.LocationApiService
import com.example.soskarikcyandmorty.data.network.dtos.toLocation
import com.example.soskarikcyandmorty.data.network.dtos.toResponse
import com.example.soskarikcyandmorty.domain.repositories.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val service: LocationApiService
) : BaseRepository(), LocationRepository {

    override fun fetchLocation(page: Int) = doRequest {
        service.fetchLocation(page).toResponse().results.map { it.toLocation() }
    }
}