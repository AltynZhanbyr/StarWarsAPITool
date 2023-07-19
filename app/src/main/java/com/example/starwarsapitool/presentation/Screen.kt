package com.example.starwarsapitool.presentation

import com.example.starwarsapitool.utils.Constants

sealed class Screen(route:String) {
    object SearchScreen:Screen(Constants.searchScreen)
}