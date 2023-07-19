package com.example.starwarsapitool.data.dto

import com.google.gson.annotations.SerializedName

data class StarshipListDto(
    val count: Int,
    val next: String,
    val previous: String,
    @SerializedName("results")
    val starshipDtoList: List<StarshipDto>
)