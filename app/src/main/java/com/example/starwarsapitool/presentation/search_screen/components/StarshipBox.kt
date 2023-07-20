package com.example.starwarsapitool.presentation.search_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship

@Composable
fun StarshipBox(
    starship: Starship,
    icon:ImageVector,
    color:Color,
    onAddToFavoriteClickListener:(Starship)->Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.LightGray)
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column(
                    modifier = Modifier.weight(0.8f)
                ){
                    Text("Name: ${starship.name}")
                    Text("Model: ${starship.model}")
                    Text("Manufacturer: ${starship.manufacturer}")
                    Text("Passengers: ${starship.passengers}")
                }
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .weight(0.2f)
                        .height(25.dp)
                        .clickable {
                            onAddToFavoriteClickListener(starship)
                        },
                    tint = color
                )
            }
        }
    }
}