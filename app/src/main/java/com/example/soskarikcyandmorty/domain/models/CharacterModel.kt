package com.example.soskarikcyandmorty.domain.models

import com.example.soskarikcyandmorty.bases.BaseInterfaceCallback

data class CharacterModel(
    override var id: Int,
    var name: String,
    var image: String,
    var status: String,
    var species: String,
    var location: Any
) : BaseInterfaceCallback
