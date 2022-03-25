package com.example.soskarikcyandmorty.presentation.models

import com.example.domain.models.EpisodeModel
import com.example.soskarikcyandmorty.presentation.base.BaseInterfaceCallback

data class EpisodeModelUI(
    override var id: Int,
    var name: String,
    var episode: String,
    val air_date: String,
    val created: String
): BaseInterfaceCallback

fun EpisodeModel.toEpisodeUI() = EpisodeModelUI(id, name, episode, air_date, created)
