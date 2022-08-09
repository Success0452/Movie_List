package com.example.movielist.room.implementation

import com.example.movielist.models.MovieResponse
import com.example.movielist.models.Results
import com.example.movielist.room.interfaces.MovieDao
import com.example.movielist.room.interfaces.RepositoryService
import com.example.movielist.room.models.Movie
import com.example.movielist.service.implementation.ApiServiceImpl
import com.example.movielist.service.interfaces.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryServiceImpl @Inject constructor(
    private val service: ApiService,
    private val movieDao: MovieDao
) : RepositoryService {

    override fun getAll(): Flow<List<Movie>> {
       return movieDao.getMovieList();
    }

    override suspend fun saveMovie(movie: Movie) {
       movieDao.saveMovie(movie)
    }

}