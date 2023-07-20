package com.example.starwarsapitool.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.starwarsapitool.presentation.search_screen.SearchScreen
import com.example.starwarsapitool.presentation.ui.theme.StarWarsAPIToolTheme
import com.example.starwarsapitool.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsAPIToolTheme {
                val scaffoldState = rememberScaffoldState()
                val coroutineScope = rememberCoroutineScope()
                val navController = rememberNavController()
                val snackbarHostState = remember{
                    SnackbarHostState()
                }
                val viewModel:MainViewModel = hiltViewModel()
                val selectedItem = viewModel.selectedItem

                Scaffold(
                    scaffoldState = scaffoldState,
                    snackbarHost ={
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    bottomBar = {
                        MyBottomNavigation(
                            _selectedItem =  selectedItem,
                            navController = navController,
                            onClickListener = {
                                navController.navigate(it){
                                    popUpTo(Constants.searchScreen){
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            onSelectedItemChangeListener = {
                                viewModel.changeSelectedItem(it)
                            }
                        )
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colors.background
                    ) {
                        NavHost(navController = navController, startDestination = Constants.searchScreen ){
                            composable(Constants.searchScreen){
                                SearchScreen(snackbarHostState,coroutineScope)
                            }
                            composable(Constants.favoriteScreen){
                                Text("Search screen")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MyBottomNavigation(
    _selectedItem:MutableState<Int>,
    navController: NavController,
    onClickListener:(String)->Unit,
    onSelectedItemChangeListener:(Int)->Unit
){
    var selectedItem = _selectedItem
    var items = listOf(Screen.SearchScreen,Screen.FavoriteScreen)
    BottomNavigation(
        modifier = Modifier.background(MaterialTheme.colors.background)
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEachIndexed{index, item->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    onSelectedItemChangeListener(index)
                    onClickListener(item.route)
                          },
                icon = {Icon(item.icon, "")},
                label = {Text(item.title)},
                selectedContentColor = MaterialTheme.colors.background,
                unselectedContentColor = Color.LightGray
            )
        }

    }
}
