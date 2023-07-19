package com.example.starwarsapitool.data.dto

data class CharListDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<CharacterDto>
)