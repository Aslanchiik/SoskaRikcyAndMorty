package com.example.domain.models

data class EpisodeModel(
    var id: Int,
    var name: String,
    var episode: String,
    val air_date: String,
    val created: String
)