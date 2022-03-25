package com.example.domain.models

sealed class RickyAndMortyModel {

    class CharactersModel(val charactersModel: CharacterModel) : RickyAndMortyModel()
    class EpisodesModel(val episodeModel: EpisodeModel) : RickyAndMortyModel()
    class LocationsModel(val locationModel: LocationModel) : RickyAndMortyModel()
}

fun CharacterModel.toCharacterModel() = RickyAndMortyModel.CharactersModel(
    CharacterModel(id, name, image, status, species, location, type, episode, gender, created)
)

fun LocationModel.toLocationModel() = RickyAndMortyModel.LocationsModel(
    LocationModel(id, name, type, dimension, created)
)

fun EpisodeModel.toEpisodeModel() = RickyAndMortyModel.EpisodesModel(
    EpisodeModel(id, name, episode, air_date, created)
)
