package com.example.starwarsapitool.domain.use_case

import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import com.example.starwarsapitool.domain.repository.RemoteStarWarsRepository
import com.example.starwarsapitool.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetRemoteStarWarsStarshipUseCaseTest {
    private lateinit var useCase: GetRemoteStarWarsStarshipUseCase
    private lateinit var mockRemoteRepository: RemoteStarWarsRepository

    @Before
    fun setUp() {
        mockRemoteRepository = mockk()
        useCase = GetRemoteStarWarsStarshipUseCase(mockRemoteRepository)
    }

    @Test
    fun `Test fetching starship successfully`() = runBlocking {
        val starshipName = "1"

        val starships = listOf(
            Starship(name = "1", model = "2", manufacturer = "3", passengers = "4")
        )

        // Mock the repository's behavior
        coEvery { mockRemoteRepository.getStarshipByName(starshipName) } returns starships

        // When the use case is invoked with the character name
        val flow: Flow<Resource<List<Starship>>> = useCase(starshipName)

        // Then the flow should emit a Resource.Success with the characters list
        flow.collect { resource ->
            if(resource is Resource.Success)
                assertTrue(resource.data?.get(0)?.equals(starships[0])?:false)
        }
    }

    @Test
    fun `Test fetching starship unsuccessfully`() = runBlocking {
        val starshipName = "2"

        val starships = listOf(
            Starship(name = "1", model = "2", manufacturer = "3", passengers = "4")
        )

        // Mock the repository's behavior
        coEvery { mockRemoteRepository.getStarshipByName(starshipName) } returns starships

        // When the use case is invoked with the character name
        val flow: Flow<Resource<List<Starship>>> = useCase(starshipName)

        // Then the flow should emit a Resource.Success with the characters list
        flow.collect { resource ->
            if(resource is Resource.Success && resource.data?.get(0)?.name == starshipName)
                assertTrue(false)
            else
                assertTrue(true)
        }
    }
}