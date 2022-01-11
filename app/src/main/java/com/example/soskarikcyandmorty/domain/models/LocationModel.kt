package com.example.soskarikcyandmorty.domain.models

import com.example.soskarikcyandmorty.bases.BaseInterfaceCallback

data class LocationModel(
    override var id: Int,
    var name: String,
    var type: String,
    val dimension: String,
    val created: String
) : BaseInterfaceCallback