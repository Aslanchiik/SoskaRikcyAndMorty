package com.example.soskarikcyandmorty.presentation.models

import com.example.domain.models.CharacterLocation
import com.example.domain.models.CharacterModel
import com.example.soskarikcyandmorty.presentation.base.BaseInterfaceCallback

data class CharacterModelUI(
    override var id: Int,
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
) : BaseInterfaceCallback

fun CharacterModel.toCharacterUI() =
    CharacterModelUI(id, name, image, status, species, location, type, episode, gender, created)
