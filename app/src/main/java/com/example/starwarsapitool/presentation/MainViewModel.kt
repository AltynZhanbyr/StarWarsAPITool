package com.example.starwarsapitool.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _selectedItem = mutableStateOf<Int>(0)
    val selectedItem = _selectedItem

    fun changeSelectedItem(idx:Int){
        _selectedItem.value = idx
    }
}