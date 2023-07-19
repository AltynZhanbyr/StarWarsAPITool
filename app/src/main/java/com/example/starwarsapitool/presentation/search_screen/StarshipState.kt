package com.example.starwarsapitool.presentation.search_screen

import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship

data class StarshipState(
    val errorMessage:String = "",
    val starships:List<Starship>? = null,
    val isLoading:Boolean = false
)