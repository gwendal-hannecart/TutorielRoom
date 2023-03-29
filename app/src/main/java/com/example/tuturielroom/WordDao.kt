package com.example.roomtutoriel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// DAO must be an interface or abstract class
@Dao
interface WordDao {

    //Query that returns a list of words sorted in ascending order.
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    // method to get all the words and have it return a List of Words.
    //FLow for observe data change
    fun getAlphabetizedWords(): Flow<List<Word>>


    // a special DAO method annotation where you don't have to provide any SQL
    // strategy ignores a new word if it's exactly the same as one already in the list
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //Declares a suspend function to insert one word.
    suspend fun insert(word: Word)

    //Query requires that you provide a SQL query
    @Query("DELETE FROM word_table")
    //Declares a suspend function to delete all the words
    suspend fun deleteAll()
}

