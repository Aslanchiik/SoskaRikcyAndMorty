package com.example.data.network.remote.dtos

import com.example.domain.models.CharacterLocation
import com.example.domain.models.CharacterModel
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
    var location: CharacterLocationModelDto,

    @SerializedName("type")
    var type: String,

    @SerializedName("episode")
    var episode: ArrayList<String>,

    @SerializedName("gender")
    var gender: String,

    @SerializedName("created")
    val created: String
    )

data class CharacterLocationModelDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)

fun CharacterModelDto.toCharacter(): CharacterModel {
    return CharacterModel(
        id,
        name,
        image,
        status,
        species,
        location.toCharacterLocation(),
        type,
        episode,
        gender,
        created
    )
}

fun CharacterLocationModelDto.toCharacterLocation(): CharacterLocation {
    return CharacterLocation(
        name,
        url
    )
}

