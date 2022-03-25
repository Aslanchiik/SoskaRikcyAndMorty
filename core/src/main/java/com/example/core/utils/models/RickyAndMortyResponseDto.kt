package com.example.core.utils.models

import com.google.gson.annotations.SerializedName

data class RickyAndMortyResponseDto<T>(

    @SerializedName("info")
    val info: InfoDto,

    @SerializedName("results")
    var results: ArrayList<T>
)