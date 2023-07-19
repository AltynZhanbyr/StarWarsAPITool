package com.example.starwarsapitool.domain.repository

import com.example.starwarsapitool.data.dto.CharListDto
import com.example.starwarsapitool.data.dto.CharacterDto
import com.example.starwarsapitool.data.dto.StarshipDto

interface RemoteStarWarsRepository {

    suspend fun getCharacterByName(name:String):List<CharacterDto>
    suspend fun getStarshipByName(name:String):List<StarshipDto>
}