package com.example.starwarsapitool.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starship_table")
data class Starship(
    @PrimaryKey(autoGenerate = true)
    val id:Long? = null,
    val name:String,
    val model:String,
    val manufacturer:String,
    val passengers:String
):StarWarsObject