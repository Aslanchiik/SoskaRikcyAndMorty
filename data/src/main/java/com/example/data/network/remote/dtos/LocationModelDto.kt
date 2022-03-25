package com.example.data.network.remote.dtos

import com.example.domain.models.LocationModel
import com.google.gson.annotations.SerializedName

data class LocationModelDto(

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("dimension")
    val dimension: String,

    @SerializedName("created")
    val created: String
)

fun LocationModelDto.toLocation(): LocationModel {
    return LocationModel(
        id,
        name,
        type,
        dimension,
        created
    )
}