package com.example.soskarikcyandmorty.domain.models

import com.example.soskarikcyandmorty.bases.BaseInterfaceCallback

data class EpisodeModel(
    override var id: Int,
    var name: String,
    var episode: String,
) : BaseInterfaceCallback