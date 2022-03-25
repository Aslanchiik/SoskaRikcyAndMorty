package com.example.data.network.apiservices

import com.example.core.utils.models.RickyAndMortyResponseDto
import com.example.data.network.dtos.EpisodeModelDto
import com.example.soskarikcyandmorty.constants.Constants.EPISODE_AND_POINT
import com.example.soskarikcyandmorty.constants.Constants.EPISODE_ID
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApiService {

    @GET(EPISODE_AND_POINT)
    suspend fun fetchEpisode(
        @Query("page") page: Int?,
        @Query("name") name: String?,
        @Query("episode") episode: String?
    ): RickyAndMortyResponseDto<EpisodeModelDto>

    @GET(EPISODE_ID)
    suspend fun fetchEpisodeId(
        @Path("id") id: Int
    ): EpisodeModelDto
}