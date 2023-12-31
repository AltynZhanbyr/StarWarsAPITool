package com.example.starwarsapitool.data.repository

import com.example.starwarsapitool.data.dto.CharListDto
import com.example.starwarsapitool.data.dto.CharacterDto
import com.example.starwarsapitool.data.dto.StarshipDto
import com.example.starwarsapitool.data.dto.toCharacter
import com.example.starwarsapitool.data.dto.toStarship
import com.example.starwarsapitool.data.remote.StarWarsApi
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import com.example.starwarsapitool.domain.repository.RemoteStarWarsRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class RemoteStarWarsRepositoryImpl @Inject constructor(private val starWarsApi: StarWarsApi):RemoteStarWarsRepository {

    override suspend fun getCharacterByName(name: String): List<Character> {
        return starWarsApi.searchByName(name).results.map{
            it.toCharacter()
        }
    }

    override suspend fun getStarshipByName(name: String): List<Starship> {
        return starWarsApi.searchStarShipByName(name).starshipDtoList.map {
            it.toStarship()
        }
    }

}