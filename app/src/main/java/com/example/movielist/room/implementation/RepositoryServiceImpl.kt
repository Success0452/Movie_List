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

// Repository Service Implementation class

class RepositoryServiceImpl(
    private val movieDao: MovieDao
) : RepositoryService {

    // override getAll function from RepositoryService for implementation
    override fun getAll(): Flow<List<Movie>> {
        // return the list of all movies present in the database
       return movieDao.getMovieList();
    }

    // override saveMovie function from RepositoryService for implementation
    override suspend fun saveMovie(movie: Movie) {
        // save item into the database
       movieDao.saveMovie(movie)
    }

}