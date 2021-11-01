package com.example.soskarikcyandmorty.domain.models

import java.util.ArrayList

data class RickyAndMortyResponse<T>(
    val info : Info,
    var results: ArrayList<T>
)