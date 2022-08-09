package com.example.movielist.viewmodel

import MoviePagingSource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.movielist.models.MovieResponse
import com.example.movielist.models.Results
import com.example.movielist.room.interfaces.RepositoryService
import com.example.movielist.room.models.Movie
import com.example.movielist.route.HttpRoute
import com.example.movielist.service.interfaces.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

// annotation specifically for viewmodel, to allow for constructor injection
@HiltViewModel

class MovieViewModel @Inject constructor(
    private  val service: ApiService,
    private val repository: RepositoryService
) : ViewModel() {

    // this variable tracks when splashscreen finish loading
    private var _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    // this serves as the entry of the viewmodel and codes here get executed first
    init {
        viewModelScope.launch {
            delay(3000L)
            _loading.value = false
        }
    }

    // this variable update the ui of the content inside the roomdb
    val roomdb = repository.getAll()

    // pager handle the flow of data into the ui
    val pager = Pager(
        config = PagingConfig(pageSize = 5, prefetchDistance = 5),
        pagingSourceFactory = { MoviePagingSource(service = service) }
    ).flow.cachedIn(viewModelScope)

    // this function when called save data into the roomdb
    fun saveMovie(movie: Results){
        viewModelScope.launch {
            repository.saveMovie(
                Movie(
                    id = movie.id,
                    list_type = movie.list_type,
                    item_count = movie.item_count,
                    iso_639_1 = movie.iso_639_1,
                    favorite_count = movie.favorite_count,
                    description = movie.description,
                    name = movie.name,
                )
            )
        }
    }

}