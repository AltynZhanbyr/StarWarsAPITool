package com.example.starwarsapitool.domain.repository

import com.example.starwarsapitool.data.dto.CharListDto
import com.example.starwarsapitool.data.dto.CharacterDto

interface RemoteStarWarsRepository {

    suspend fun getCharacterByName(name:String):CharListDto
}