package com.example.starwarsapitool.presentation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.starwarsapitool.utils.Constants

sealed class Screen(val route:String,val icon:ImageVector,val title:String) {
    object SearchScreen:Screen(Constants.searchScreen, Icons.Filled.Search,"Search")
    object FavoriteScreen:Screen(Constants.favoriteScreen,Icons.Filled.Favorite,"Favorite")
}