package com.example.starwarsapitool.data.dto

import android.util.Log
import com.example.starwarsapitool.domain.model.Character

data class CharacterDto(
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>,
    val created: String,
    val edited: String,
    val url: String
)
fun CharacterDto.toCharacter(): Character {
    return Character(
        name = name,
        gender = gender,
        starships = starships.size
    )
}