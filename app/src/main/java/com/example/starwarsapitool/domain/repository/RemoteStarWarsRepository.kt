package com.example.starwarsapitool.domain.repository

import com.example.starwarsapitool.data.dto.CharListDto
import com.example.starwarsapitool.data.dto.CharacterDto
import com.example.starwarsapitool.data.dto.StarshipDto
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship

interface RemoteStarWarsRepository {

    suspend fun getCharacterByName(name:String):List<Character>
    suspend fun getStarshipByName(name:String):List<Starship>
}