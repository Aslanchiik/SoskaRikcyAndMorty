package com.example.soskarikcyandmorty.domain.models

import com.example.soskarikcyandmorty.bases.BaseInterfaceCallback

data class CharacterModel(
    override var id: Int,
    var name: String,
    var image: String,
    var status: String,
    var species: String,
    var location: CharacterLocation,
    var type: String,
    val episode: ArrayList<String>,
    var gender: String,
    val created: String
) : BaseInterfaceCallback

data class CharacterLocation(
    val name: String,
    val url: String
)
