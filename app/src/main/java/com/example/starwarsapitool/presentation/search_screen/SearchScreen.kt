package com.example.starwarsapitool.presentation.search_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.starwarsapitool.presentation.search_screen.components.SearchComponent

@Composable
fun SearchScreen(
    viewModel:SearchScreenViewModel = hiltViewModel()
) {
    val characterState = viewModel.characterState.value
    val starshipState = viewModel.starshipState.value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            SearchComponent( viewModel){
                viewModel.searchCharacterByName(it)
            }
            Spacer(
                modifier = Modifier.height(15.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                ){
                if(characterState.isLoading)
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                if(!characterState.characters.isNullOrEmpty()){
                    Text(characterState.characters.toString())
                }
                if(characterState.errorMessage.isNotEmpty()) {
                    if(!starshipState.starships.isNullOrEmpty())
                        Text(starshipState.starships.toString())
                    else
                        Text(characterState.errorMessage)
                }
            }
        }
    }
}