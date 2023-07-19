package com.example.starwarsapitool.presentation.search_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.starwarsapitool.presentation.search_screen.SearchScreenViewModel

@Composable
fun SearchComponent(
    viewModel: SearchScreenViewModel,
    onSearchClickListener:(String)->Unit
) {
    var searchState = viewModel.searchText.value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = searchState,
                onValueChange = {viewModel.changeSearchText(it)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.weight(0.8f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = {
                onSearchClickListener(searchState)
            },
                enabled = searchState.length>=2 && searchState.isNotEmpty(),
                modifier = Modifier.weight(0.4f)
            ) {
                Text(text = "Search")
            }
        }
    }
}