package com.example.starwarsapitool.data.dto

data class CharListDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<CharacterDto>
)