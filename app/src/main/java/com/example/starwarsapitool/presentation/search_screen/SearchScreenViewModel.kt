package com.example.starwarsapitool.presentation.search_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.use_case.GetRemoteStarWarsCharacterUseCase
import com.example.starwarsapitool.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getRemoteStarWarsCharacterUseCase: GetRemoteStarWarsCharacterUseCase
):ViewModel() {

    private val _charactersState = mutableStateOf<CharacterState>(CharacterState())
    val characterState = _charactersState

    private val _searchText = mutableStateOf("")
    val searchText = _searchText

    fun searchCharacterByName(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            getRemoteStarWarsCharacterUseCase(name).collect(){res->
                when(res){
                    is Resource.Loading ->
                        _charactersState.value = CharacterState(isLoading = true)
                    is Resource.Success ->
                        searchCharacterByNameInCollection(res.data,name)
                    is Resource.Error ->
                        _charactersState.value = CharacterState(errorMessage = res.message?:"Unexpected Error occurred")
                }
            }
        }
    }

    fun changeSearchText(name:String){
        _searchText.value = name
    }

    private suspend fun searchCharacterByNameInCollection(characters: List<Character>?, name: String) {
        val newCharacters = mutableListOf<Character>()
        var isCharacterExists = false
        characters?.forEach {
            if(it.name.contains(name, ignoreCase = true)){
                isCharacterExists = true
                Log.d("SearchScreenViewModel", it.name)
               newCharacters.add(it)
            }
        }
        if(!isCharacterExists)
            _charactersState.value = CharacterState("There is no such character")
        else
            _charactersState.value = CharacterState(characters = newCharacters)
    }
}