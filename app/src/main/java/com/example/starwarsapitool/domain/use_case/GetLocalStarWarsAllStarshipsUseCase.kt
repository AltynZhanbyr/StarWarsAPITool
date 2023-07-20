package com.example.starwarsapitool.domain.use_case

import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import com.example.starwarsapitool.domain.repository.LocalStarWarsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalStarWarsAllStarshipsUseCase @Inject constructor(
    private val repository: LocalStarWarsRepository
) {
    operator fun invoke(): Flow<List<Starship>> {
        return repository.getAllStarships()
    }
}