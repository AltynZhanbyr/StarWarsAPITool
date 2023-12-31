package com.example.starwarsapitool.presentation.search_screen

import com.example.starwarsapitool.domain.model.Character

data class CharacterState(
    val errorMessage:String = "",
    val characters:List<Character>? = null,
    val isLoading:Boolean = false
)