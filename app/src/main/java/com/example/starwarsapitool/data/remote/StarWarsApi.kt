package com.example.starwarsapitool.data.remote

import com.example.starwarsapitool.data.dto.CharListDto
import com.example.starwarsapitool.data.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsApi {
    @GET("api/people")
    suspend fun searchByName(
//        @Query("name") name: String
    ): CharListDto

    @GET
    suspend fun getCharactersFromUrl(@Url url: String): CharListDto
}