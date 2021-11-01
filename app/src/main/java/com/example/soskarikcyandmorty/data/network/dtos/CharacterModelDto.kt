package com.example.soskarikcyandmorty.data.network.dtos

import com.example.soskarikcyandmorty.domain.models.CharacterModel
import com.google.gson.annotations.SerializedName

data class CharacterModelDto(

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("image")
    var image: String,

    @SerializedName("status")
    var status: String,

    @SerializedName("species")
    var species: String,

    @SerializedName("location")
    var location: Any
)

fun CharacterModelDto.toCharacter(): CharacterModel {
    return CharacterModel(
        id,
        name,
        image,
        status,
        species,
        location
    )
}