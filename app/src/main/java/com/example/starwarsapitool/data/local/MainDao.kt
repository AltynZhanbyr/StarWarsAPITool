package com.example.starwarsapitool.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {
    //Character table SQL Commands:

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("delete from character_table")
    suspend fun deleteAllCharacters()

    @Update
    suspend fun updateCharacter(character: Character)

    @Query("select * from character_table")
    fun getAllCharacters(): Flow<List<Character>>

    //Starship table SQL Commands:

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarship(starship: Starship)

    @Delete
    suspend fun deleteStarship(starship: Starship)

    @Query("delete from starship_table")
    suspend fun deleteAllStarships()

    @Update
    suspend fun updateStarship(starship: Starship)

    @Query("select * from starship_table")
    fun getAllStarships(): Flow<List<Starship>>

}