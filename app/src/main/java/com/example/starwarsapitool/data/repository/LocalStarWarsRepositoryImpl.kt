package com.example.starwarsapitool.data.repository

import com.example.starwarsapitool.data.local.MainDB
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import com.example.starwarsapitool.domain.repository.LocalStarWarsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalStarWarsRepositoryImpl @Inject constructor(
    private val db:MainDB
):LocalStarWarsRepository {
    //Characters methods
    override suspend fun insertCharacter(character: Character) {
        return db.getDao().insertCharacter(character)
    }

    override suspend fun deleteCharacter(character: Character) {
        return db.getDao().deleteCharacter(character)
    }

    override suspend fun deleteAllCharacters() {
        return db.getDao().deleteAllCharacters()
    }

    override suspend fun updateCharacter(character: Character) {
        return db.getDao().updateCharacter(character)
    }

    override fun getAllCharacters(): Flow<List<Character>> {
        return db.getDao().getAllCharacters()
    }

    //Starship methods
    override suspend fun insertStarship(starship: Starship) {
        return db.getDao().insertStarship(starship)
    }

    override suspend fun deleteStarship(starship: Starship) {
        return db.getDao().deleteStarship(starship)
    }

    override suspend fun deleteAllStarships() {
        return db.getDao().deleteAllStarships()
    }

    override suspend fun updateStarship(starship: Starship) {
        return db.getDao().updateStarship(starship)
    }

    override fun getAllStarships(): Flow<List<Starship>> {
        return db.getDao().getAllStarships()
    }

}