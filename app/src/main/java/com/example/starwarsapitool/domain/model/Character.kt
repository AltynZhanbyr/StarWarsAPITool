package com.example.starwarsapitool.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id:Long? = null,
    val name:String,
    val gender:String,
    val starships:Int
):StarWarsObject