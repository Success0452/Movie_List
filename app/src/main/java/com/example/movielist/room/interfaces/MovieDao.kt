package com.example.movielist.room.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movielist.room.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert
    suspend fun saveMovie(movie: Movie)

    @Query("select * from movie_list")
    fun getMovieList(): Flow<List<Movie>>

    @Query("delete from movie_list")
    fun deleteAll()

}