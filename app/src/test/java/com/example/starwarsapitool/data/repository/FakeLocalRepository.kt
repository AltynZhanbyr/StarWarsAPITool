package com.example.starwarsapitool.data.repository

import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import com.example.starwarsapitool.domain.repository.LocalStarWarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalRepository():LocalStarWarsRepository {
    var insertCharacterCalled = false
    var insertedCharacter: Character? = null

    var insertStarshipCalled = false
    var insertedStarship:Starship? = null

    var allCharacters= mutableListOf<Character>()
    var allStarships= mutableListOf<Starship>()

    override suspend fun insertCharacter(character: Character) {
        allCharacters.add(character)
    }

    override suspend fun deleteCharacter(character: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllCharacters() {
        TODO("Not yet implemented")
    }

    override suspend fun updateCharacter(character: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterByName(name: String): Character? {
        TODO("Not yet implemented")
    }

    override fun getAllCharacters(): Flow<List<Character>> = flow{
        emit(allCharacters)
    }

    override suspend fun insertStarship(starship: Starship) {
        allStarships.add(starship)
    }

    override suspend fun deleteStarship(starship: Starship) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllStarships() {
        TODO("Not yet implemented")
    }

    override suspend fun updateStarship(starship: Starship) {
        TODO("Not yet implemented")
    }

    override suspend fun getStarshipByName(name: String): Starship? {
        TODO("Not yet implemented")
    }

    override fun getAllStarships(): Flow<List<Starship>> = flow{
        emit(allStarships)
    }
}