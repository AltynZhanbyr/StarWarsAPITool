package com.example.starwarsapitool.domain.use_case

data class AllLocalUseCases(
    val deleteLocalStarWarsCharacterUseCase: DeleteLocalStarWarsCharacterUseCase,
    val insertLocalStarWarsCharacterUseCase: InsertLocalStarWarsCharacterUseCase,
    val getLocalStarWarsAllCharactersUseCase: GetLocalStarWarsAllCharactersUseCase,
    val deleteLocalStarWarsStarshipUseCase:DeleteLocalStarWarsStarshipUseCase,
    val insertLocalStarWarsStarshipUseCase: InsertLocalStarWarsStarshipUseCase,
    val getLocalStarWarsAllStarshipsUseCase: GetLocalStarWarsAllStarshipsUseCase
)