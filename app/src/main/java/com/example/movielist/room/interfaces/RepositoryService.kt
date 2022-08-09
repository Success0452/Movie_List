package com.example.movielist.room.interfaces

import com.example.movielist.models.Results
import com.example.movielist.room.models.Movie
import kotlinx.coroutines.flow.Flow

interface RepositoryService {

    fun getAll(): Flow<List<Movie>>

    suspend fun saveMovie(movie: Movie)
}