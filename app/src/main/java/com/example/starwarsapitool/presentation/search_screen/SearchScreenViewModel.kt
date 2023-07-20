package com.example.starwarsapitool.presentation.search_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import com.example.starwarsapitool.domain.use_case.AllLocalUseCases
import com.example.starwarsapitool.domain.use_case.GetRemoteStarWarsCharacterUseCase
import com.example.starwarsapitool.domain.use_case.GetRemoteStarWarsStarshipUseCase
import com.example.starwarsapitool.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getRemoteStarWarsCharacterUseCase: GetRemoteStarWarsCharacterUseCase,
    private val getRemoteStarWarsStarshipUseCase: GetRemoteStarWarsStarshipUseCase,
    private val allLocalUseCases: AllLocalUseCases
):ViewModel() {

    private val _charactersState = mutableStateOf<CharacterState>(CharacterState())
    val characterState = _charactersState

    private val _starshipState = mutableStateOf<StarshipState>(StarshipState())
    val starshipState = _starshipState

    private val _searchText = mutableStateOf("")
    val searchText = _searchText

    fun searchCharacterByName(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            getRemoteStarWarsCharacterUseCase(name).collect(){res->
                when(res){
                    is Resource.Loading ->
                        _charactersState.value = CharacterState(isLoading = true)
                    is Resource.Success ->
                        _charactersState.value = CharacterState(characters = res.data)
                    is Resource.Error -> {
                        _charactersState.value =
                            CharacterState(errorMessage = res.message.toString())
                        searchStarshipByName(name)
                    }
                }
            }
        }
    }

    fun insertStarship(starship: Starship){
        viewModelScope.launch {
            val existingStarship = allLocalUseCases.getLocalStarWarsStarshipByNameUseCase(starship.name)
            if(existingStarship==null){
                allLocalUseCases.insertLocalStarWarsStarshipUseCase(starship)
                delay(1000L)
            }
        }
    }

    fun insertCharacter(character: Character){
        viewModelScope.launch {
            val existingCharacter = allLocalUseCases.getLocalStarWarsCharacterByNameUseCase(character.name)
            if(existingCharacter==null){
                allLocalUseCases.insertLocalStarWarsCharacterUseCase(character)
                delay(1000L)
            }
        }
    }

    fun changeSearchText(name:String){
        _searchText.value = name
    }

    private fun searchStarshipByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getRemoteStarWarsStarshipUseCase(name).collect(){res->
                if(res is Resource.Success)
                    _starshipState.value = StarshipState(starships = res.data)
                if(res is Resource.Error)
                    _starshipState.value = StarshipState(errorMessage = res.message?:"Unexpected Error")
            }
        }
    }
}