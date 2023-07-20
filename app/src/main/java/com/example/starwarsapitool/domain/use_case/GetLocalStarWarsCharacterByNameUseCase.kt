package com.example.starwarsapitool.domain.use_case

import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import com.example.starwarsapitool.domain.repository.LocalStarWarsRepository
import javax.inject.Inject

class GetLocalStarWarsCharacterByNameUseCase @Inject constructor(
    private val repository: LocalStarWarsRepository
) {
    suspend operator fun invoke(name:String): Character? {
        return repository.getCharacterByName(name)
    }
}