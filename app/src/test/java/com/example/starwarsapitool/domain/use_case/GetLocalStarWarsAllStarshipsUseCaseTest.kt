package com.example.starwarsapitool.domain.use_case

import com.example.starwarsapitool.data.repository.FakeLocalRepository
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetLocalStarWarsAllStarshipsUseCaseTest {
    private lateinit var fakeLocalRepository: FakeLocalRepository
    private lateinit var getLocalStarWarsAllStarshipsUseCase: GetLocalStarWarsAllStarshipsUseCase
    private lateinit var starships: List<Starship>

    @Before
    fun setUp() {
        fakeLocalRepository = FakeLocalRepository()
        getLocalStarWarsAllStarshipsUseCase = GetLocalStarWarsAllStarshipsUseCase(fakeLocalRepository)

        val starship1 = Starship(name = "1", model = "2", manufacturer = "3", passengers = "4")
        val starship2 = Starship(name = "1", model = "2", manufacturer = "3", passengers = "4")

        starships = listOf(starship1,starship2)

        runBlocking {
            starships.forEach{
                fakeLocalRepository.insertStarship(it)
            }
        }
    }

    @Test
    fun `Test of getting all characters from local DB`() = runBlocking{
        getLocalStarWarsAllStarshipsUseCase().collect{
            assertTrue(it.size == starships.size)
        }

    }
}