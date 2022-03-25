package com.example.data.network.remote.dtos

import com.example.domain.models.EpisodeModel
import com.google.gson.annotations.SerializedName

data class EpisodeModelDto(

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("episode")
    var episode: String,

    @SerializedName("air_date")
    val air_date: String,

    @SerializedName("created")
    val created: String
)

fun EpisodeModelDto.toEpisode(): EpisodeModel {
    return EpisodeModel(
        id,
        name,
        episode,
        air_date,
        created
    )
}