package com.example.starwarsapitool.data.dto

import com.example.starwarsapitool.domain.model.Starship

data class StarshipDto(
    val MGLT: String,
    val cargo_capacity: String,
    val consumables: String,
    val cost_in_credits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    val hyperdrive_rating: String,
    val length: String,
    val manufacturer: String,
    val max_atmosphering_speed: String,
    val model: String,
    val name: String,
    val passengers: String,
    val pilots: List<String>,
    val starship_class: String,
    val url: String
)

fun StarshipDto.toStarship():Starship{
    return Starship(
        name = name,
        model = model,
        manufacturer = manufacturer,
        passengers = passengers
    )
}