package com.example.movielist.service.interfaces

import android.util.Size
import com.example.movielist.models.MovieResponse

interface ApiService {

    suspend fun loadMovie(page: Int, size: Int): MovieResponse?

}