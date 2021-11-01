package com.example.soskarikcyandmorty.data.network.apiservices

import com.example.soskarikcyandmorty.constants.Constants.EPISODE_AND_POINT
import com.example.soskarikcyandmorty.data.network.dtos.EpisodeModelDto
import com.example.soskarikcyandmorty.data.network.dtos.RickyAndMortyResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApiService {

    @GET(EPISODE_AND_POINT)
    suspend fun fetchEpisode(
        @Query("page") page: Int? = null
    ): RickyAndMortyResponseDto<EpisodeModelDto>
}