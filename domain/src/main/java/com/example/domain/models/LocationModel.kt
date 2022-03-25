package com.example.domain.models

data class LocationModel(
    var id: Int,
    var name: String,
    var type: String,
    val dimension: String,
    val created: String
)