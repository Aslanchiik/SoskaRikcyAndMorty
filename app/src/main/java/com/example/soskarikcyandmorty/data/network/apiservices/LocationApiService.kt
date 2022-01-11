package com.example.soskarikcyandmorty.data.network.apiservices

import com.example.soskarikcyandmorty.constants.Constants.LOCATION_AND_POINT
import com.example.soskarikcyandmorty.constants.Constants.LOCATION_ID
import com.example.soskarikcyandmorty.data.network.dtos.LocationModelDto
import com.example.soskarikcyandmorty.data.network.dtos.RickyAndMortyResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiService {

    @GET(LOCATION_AND_POINT)
    suspend fun fetchLocation(
        @Query("page") page: Int?,
        @Query("name") name: String?,
        @Query("type") type: String?,
        @Query("dimension") dimension: String?
    ): RickyAndMortyResponseDto<LocationModelDto>

    @GET(LOCATION_ID)
    suspend fun fetchLocationId(
        @Path("id") id: Int
    ): LocationModelDto
}