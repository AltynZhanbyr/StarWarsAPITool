package com.example.starwarsapitool.domain.use_case

import com.example.starwarsapitool.domain.model.Starship
import com.example.starwarsapitool.domain.repository.LocalStarWarsRepository
import javax.inject.Inject

class DeleteLocalStarWarsStarshipUseCase @Inject constructor(
    private val repository: LocalStarWarsRepository
) {
    suspend operator fun invoke(starship: Starship){
        repository.deleteStarship(starship)
    }
}