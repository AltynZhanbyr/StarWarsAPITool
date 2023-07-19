package com.example.starwarsapitool.data.remote

import com.example.starwarsapitool.data.dto.CharListDto
import com.example.starwarsapitool.data.dto.CharacterDto
import com.example.starwarsapitool.data.dto.StarshipListDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsApi {

    @GET("api/people?")
    suspend fun searchByName(
        @Query("search") name: String
    ): CharListDto

    @GET("api/starships?")
    suspend fun searchStarShipByName(
        @Query("search") name: String
    ): StarshipListDto

}