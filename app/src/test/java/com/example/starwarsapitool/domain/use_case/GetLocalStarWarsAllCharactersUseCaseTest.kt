package com.example.starwarsapitool.domain.use_case

import com.example.starwarsapitool.data.repository.FakeLocalRepository
import com.example.starwarsapitool.domain.model.Character
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetLocalStarWarsAllCharactersUseCaseTest {
    private lateinit var fakeLocalRepository: FakeLocalRepository
    private lateinit var getLocalStarWarsAllCharactersUseCase: GetLocalStarWarsAllCharactersUseCase
    private lateinit var characters: List<Character>

    @Before
    fun setUp() {
        fakeLocalRepository = FakeLocalRepository()
        getLocalStarWarsAllCharactersUseCase = GetLocalStarWarsAllCharactersUseCase(fakeLocalRepository)

        val character1 = Character(name = "Luke Skywalker", gender = "male" , starships = 2)
        val character2 = Character(name = "Anakin Skywalker", gender = "male" , starships = 2)

        characters = listOf(character1,character2)

        runBlocking {
            characters.forEach{
                fakeLocalRepository.insertCharacter(it)
            }
        }
    }

    @Test
    fun `Test of getting all characters from local DB`() = runBlocking{
        getLocalStarWarsAllCharactersUseCase().collect{
            assertTrue(it.size == characters.size)
        }

    }
}