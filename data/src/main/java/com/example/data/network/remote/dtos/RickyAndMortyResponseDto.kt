package com.example.data.network.remote.dtos

import com.google.gson.annotations.SerializedName

data class RickyAndMortyResponseDto<T>(

    @SerializedName("info")
    val info: InfoDto,

    @SerializedName("results")
    var results: ArrayList<T>
)