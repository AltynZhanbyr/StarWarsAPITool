package com.example.starwarsapitool.presentation.favorite_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import com.example.starwarsapitool.domain.use_case.AllLocalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    private val allLocalUseCases: AllLocalUseCases
): ViewModel(){

    private val _allCharacters = mutableStateListOf<Character>()
    val allCharacters get() = _allCharacters

    private val _allStarships = mutableStateListOf<Starship>()
    val allStarships get() = _allStarships

    init {
        viewModelScope.launch {
            allLocalUseCases.getLocalStarWarsAllCharactersUseCase().collect {
                _allCharacters.clear()
                _allCharacters.addAll(it)
            }
        }

        viewModelScope.launch {
            allLocalUseCases.getLocalStarWarsAllStarshipsUseCase().collect {
                _allStarships.clear()
                _allStarships.addAll(it)
            }
        }
    }

    fun deleteCharacter(character: Character){
        viewModelScope.launch {
            allLocalUseCases.deleteLocalStarWarsCharacterUseCase(character)
        }
    }

    fun deleteStarship(starship: Starship){
        viewModelScope.launch {
            allLocalUseCases.deleteLocalStarWarsStarshipUseCase(starship)
        }
    }
}
