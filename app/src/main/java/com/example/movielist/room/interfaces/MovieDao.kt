package com.example.movielist.room.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movielist.room.models.Movie
import kotlinx.coroutines.flow.Flow
// MovieDao interface class to declare functions to be used in the database

// Dao annotation to declare this class as a dao class
@Dao
interface MovieDao {

    // insert annotation to assign this function and implementation, that is only handled by roomDB
    @Insert
    suspend fun saveMovie(movie: Movie)

    // query annotation to specify the kind of implementation for this function
    @Query("select * from movie_list")
    fun getMovieList(): Flow<List<Movie>>

    // query annotation to specify the kind of implementation for this function
    @Query("delete from movie_list")
    fun deleteAll()

}