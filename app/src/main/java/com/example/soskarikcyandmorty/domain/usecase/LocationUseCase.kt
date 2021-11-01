package com.example.soskarikcyandmorty.domain.usecase

import com.example.soskarikcyandmorty.domain.repositories.LocationRepository
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(page: Int) = repository.fetchLocation(page)
}