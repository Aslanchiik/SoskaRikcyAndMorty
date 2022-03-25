package com.example.domain.models

import com.example.common.base.BaseInterfaceCallback

data class LocationModel(
    override var id: Int,
    var name: String,
    var type: String,
    val dimension: String,
    val created: String
) : BaseInterfaceCallback