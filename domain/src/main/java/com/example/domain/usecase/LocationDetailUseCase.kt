package com.example.domain.usecase

import com.example.domain.repositories.LocationRepository
import javax.inject.Inject

class LocationDetailUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(id: Int) = repository.fetchLocationId(id)
}