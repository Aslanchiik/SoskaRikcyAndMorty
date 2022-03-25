package com.example.domain.models


data class CharacterModel(
    var id: Int,
    var name: String,
    var image: String,
    var status: String,
    var species: String,
    var location: CharacterLocation,
    var type: String,
    val episode: MutableList<String>,
    var gender: String,
    val created: String,

    var firstSeenIn: String = ""
)

data class CharacterLocation(
    val name: String,
    val url: String
)
