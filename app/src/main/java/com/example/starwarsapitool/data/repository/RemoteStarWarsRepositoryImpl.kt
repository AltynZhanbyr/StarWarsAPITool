package com.example.starwarsapitool.data.repository

import com.example.starwarsapitool.data.dto.CharListDto
import com.example.starwarsapitool.data.dto.CharacterDto
import com.example.starwarsapitool.data.remote.StarWarsApi
import com.example.starwarsapitool.domain.repository.RemoteStarWarsRepository
import javax.inject.Inject

class RemoteStarWarsRepositoryImpl @Inject constructor(private val starWarsApi: StarWarsApi):RemoteStarWarsRepository {

    override suspend fun getCharacterByName(name: String): CharListDto {
        return starWarsApi.searchByName(
//            name
        )
    }
}