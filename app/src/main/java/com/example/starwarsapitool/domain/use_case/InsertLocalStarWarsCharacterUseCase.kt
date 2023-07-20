package com.example.starwarsapitool.domain.use_case

import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.repository.LocalStarWarsRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class InsertLocalStarWarsCharacterUseCase @Inject constructor(
    private val repository: LocalStarWarsRepository
){
    @Throws
    suspend operator fun invoke(character: Character){
        if(character==null)
            throw NullPointerException("Null object cannot be added into database")
        repository.insertCharacter(character)
    }
}