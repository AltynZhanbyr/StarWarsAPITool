package com.example.starwarsapitool.domain.use_case

import com.example.starwarsapitool.data.remote.StarWarsApi
import com.example.starwarsapitool.data.repository.RemoteStarWarsRepositoryImpl
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.repository.RemoteStarWarsRepository
import com.example.starwarsapitool.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetRemoteStarWarsCharacterUseCaseTest {
    private lateinit var useCase: GetRemoteStarWarsCharacterUseCase
    private lateinit var mockRemoteRepository: RemoteStarWarsRepository

    private lateinit var apiService: StarWarsApi
    private lateinit var starWarsRepository: RemoteStarWarsRepository
    private lateinit var mockWebServer: MockWebServer
    @Before
    fun setUp() {
        mockRemoteRepository = mockk()
        useCase = GetRemoteStarWarsCharacterUseCase(mockRemoteRepository)

        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarWarsApi::class.java)

        starWarsRepository = RemoteStarWarsRepositoryImpl(apiService)
    }

    @Test
    fun `Test fetching character successfully`() = runBlocking {
        val characterName = "Luke Skywalker"

        val characters = listOf(
            com.example.starwarsapitool.domain.model.Character(
                name = "Luke Skywalker",
                gender = "male",
                starships = 2
            )
        )

        // Mock the repository's behavior
        coEvery { mockRemoteRepository.getCharacterByName(characterName) } returns characters

        // When the use case is invoked with the character name
        val flow: Flow<Resource<List<Character>>> = useCase(characterName)

        // Then the flow should emit a Resource.Success with the characters list
        flow.collect { resource ->
            if(resource is Resource.Success)
                assertTrue(resource.data?.get(0)?.equals(characters[0])?:false)
        }
    }

    @Test
    fun `Test fetching character unsuccessfully`() = runBlocking {
        val characterName = "Shrek"

        val characters = listOf<Character>()

        // Mock the repository's behavior
        coEvery { mockRemoteRepository.getCharacterByName(characterName) } returns characters

        // When the use case is invoked with the character name
        val flow: Flow<Resource<List<Character>>> = useCase(characterName)

        // Then the flow should emit a Resource.Success with the characters list
        flow.collect { resource ->
            if(resource is Resource.Success && resource.data?.size?:0>0)
                assertTrue(false)
            else
                assertTrue(true)
        }
    }
}