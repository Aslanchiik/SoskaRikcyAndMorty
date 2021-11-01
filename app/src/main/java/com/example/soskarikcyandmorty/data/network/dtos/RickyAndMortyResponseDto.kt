package com.example.soskarikcyandmorty.data.network.dtos

import com.example.soskarikcyandmorty.domain.models.Info
import com.example.soskarikcyandmorty.domain.models.RickyAndMortyResponse
import com.google.gson.annotations.SerializedName

data class RickyAndMortyResponseDto<T>(

    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    var results: ArrayList<T>
)

fun <T> RickyAndMortyResponseDto<T>.toResponse(): RickyAndMortyResponse<T> {
    return RickyAndMortyResponse(
        info,
        results
    )
}