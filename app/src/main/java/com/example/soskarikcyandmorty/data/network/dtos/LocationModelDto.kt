package com.example.soskarikcyandmorty.data.network.dtos

import com.example.soskarikcyandmorty.domain.models.LocationModel
import com.google.gson.annotations.SerializedName

data class LocationModelDto(

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("type")
    var type: String,
)

fun LocationModelDto.toLocation(): LocationModel {
    return LocationModel(
        id,
        name,
        type
    )
}