package com.example.domain.models

import com.example.common.base.BaseInterfaceCallback

data class EpisodeModel(
    override var id: Int,
    var name: String,
    var episode: String,
    val air_date: String,
    val created: String
) : BaseInterfaceCallback