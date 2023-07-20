package com.example.starwarsapitool.domain.repository

import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import kotlinx.coroutines.flow.Flow

interface LocalStarWarsRepository {
    //Characters methods
    suspend fun insertCharacter(character: Character)
    suspend fun deleteCharacter(character: Character)
    suspend fun deleteAllCharacters()
    suspend fun updateCharacter(character: Character)
    suspend fun getCharacterByName(name:String):Character?
    fun getAllCharacters(): Flow<List<Character>>

    //Starship methods
    suspend fun insertStarship(starship: Starship)
    suspend fun deleteStarship(starship: Starship)
    suspend fun deleteAllStarships()
    suspend fun updateStarship(starship: Starship)
    suspend fun getStarshipByName(name:String):Starship?
    fun getAllStarships():Flow<List<Starship>>

}