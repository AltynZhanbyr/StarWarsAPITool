package com.example.starwarsapitool.presentation.search_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.starwarsapitool.presentation.search_screen.components.CharacterBox
import com.example.starwarsapitool.presentation.search_screen.components.SearchComponent
import com.example.starwarsapitool.presentation.search_screen.components.StarshipBox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
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
                    .align(Alignment.CenterHorizontally)
                ){
                if(characterState.isLoading)
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

                if(!characterState.characters.isNullOrEmpty()){
                    LazyColumn{
                        itemsIndexed(characterState.characters){idx,char->
                            CharacterBox(character = char){
                                coroutineScope.launch {
                                    showSnackBar("Character added successfully",snackbarHostState)
                                }
                            }
                        }
                    }
                }

                if(characterState.errorMessage.isNotEmpty()) {

                    if(!starshipState.starships.isNullOrEmpty()){
                        LazyColumn{
                            itemsIndexed(starshipState.starships){idx,starship->
                                StarshipBox(starship = starship){
                                    coroutineScope.launch {
                                        showSnackBar("Starship added successfully",snackbarHostState)
                                    }
                                }
                            }
                        }
                    }

                    else if(starshipState.errorMessage.isNotEmpty())
                        Text(starshipState.errorMessage)

                }
            }
        }
    }
}

suspend fun showSnackBar(s: String, snackbarHostState: SnackbarHostState) {
    val action = snackbarHostState.showSnackbar(
        message = s,
        duration = SnackbarDuration.Short,
        actionLabel = "Hide"
    )

    if(action == SnackbarResult.ActionPerformed)
        snackbarHostState.currentSnackbarData?.dismiss()
}
