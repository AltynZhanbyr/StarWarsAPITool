package com.example.starwarsapitool.presentation.favorite_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.StarWarsObject
import com.example.starwarsapitool.domain.model.Starship
import com.example.starwarsapitool.presentation.search_screen.ObjectsColumn
import com.example.starwarsapitool.presentation.search_screen.components.CharacterBox
import com.example.starwarsapitool.presentation.search_screen.components.SearchComponent
import com.example.starwarsapitool.presentation.search_screen.components.StarshipBox
import com.example.starwarsapitool.presentation.search_screen.showSnackBar
import com.example.starwarsapitool.presentation.utlis.CustomAlertDialog
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    viewModel: FavoriteScreenViewModel = hiltViewModel()
) {
    val characters = viewModel.allCharacters
    val starships = viewModel.allStarships

    val objects = mutableListOf<Pair<List<StarWarsObject>,String>>(Pair(characters,"Characters"),Pair(starships,"Starships"))

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
       LazyColumn(
           modifier = Modifier
               .fillMaxSize()
               .padding(5.dp)
       ){
           itemsIndexed(objects){_,obj->
               Spacer(modifier = Modifier.height(10.dp))
               Text(
                   text = obj.second,
                   fontWeight = FontWeight.Bold,
                   fontSize = 25.sp
               )
               Spacer(modifier = Modifier.height(10.dp))
               obj.first.forEach {starWarsObject ->
                   ObjectBoxes(obj = starWarsObject){
                       when(it){
                           is Character->{
                               viewModel.deleteCharacter(it)
                           }
                           is Starship->{
                                viewModel.deleteStarship(it)
                           }
                       }
                   }
               }
           }
       }
    }
}

@Composable
fun <T> ObjectBoxes(
    obj:T,
    onDeleteClickListener:(T)->Unit
){
    val isAlertDialogActive = remember{
        mutableStateOf(false)
    }
    if(obj is Starship){
        StarshipBox(starship = obj, Icons.Default.Delete,color = Color.Red){
            isAlertDialogActive.value = true
        }
        if(isAlertDialogActive.value) {
            CustomAlertDialog(isAlertDialogActive,title = "Deleting Starship", message = "Are you sure?", obj = obj) {
                onDeleteClickListener(it)
            }
        }
    }
    if(obj is Character){
        CharacterBox(character = obj,Icons.Default.Delete,color = Color.Red){
            isAlertDialogActive.value = true
        }
        if(isAlertDialogActive.value) {
            CustomAlertDialog(isAlertDialogActive,title = "Deleting Character", message = "Are you sure?", obj = obj) {
                onDeleteClickListener(it)
            }
        }
    }
}
