package com.example.soskarikcyandmorty.data.network.dtos

import com.example.soskarikcyandmorty.domain.models.EpisodeModel
import com.google.gson.annotations.SerializedName

data class EpisodeModelDto(

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("episode")
    var episode: String,
)

fun EpisodeModelDto.toEpisode(): EpisodeModel {
    return EpisodeModel(
        id,
        name,
        episode
    )
}