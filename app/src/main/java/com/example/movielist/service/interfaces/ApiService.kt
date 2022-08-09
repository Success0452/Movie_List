package com.example.movielist.service.interfaces

import android.util.Size
import com.example.movielist.models.MovieResponse

// interface class for the implementation of Apiservice
interface ApiService {

    // instance of loadMovies function
    suspend fun loadMovie(page: Int, size: Int): MovieResponse?

}