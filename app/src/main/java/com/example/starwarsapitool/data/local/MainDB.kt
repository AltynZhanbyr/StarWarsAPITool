package com.example.starwarsapitool.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship

@Database(
    entities = [Character::class, Starship::class],
    version = 1,
    exportSchema = true
)
abstract class MainDB():RoomDatabase(){
    abstract fun getDao():MainDao
}