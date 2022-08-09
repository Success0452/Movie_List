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

@HiltViewModel
class MovieViewModel @Inject constructor(
    private  val service: ApiService,
    private val repository: RepositoryService
) : ViewModel() {

    private var _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000L)
            _loading.value = false
        }
    }

    val roomdb = repository.getAll()

    private val _movieList = MutableStateFlow<List<Results>>(emptyList())
    val movieList = _movieList.asStateFlow()

    val pager = Pager(
        config = PagingConfig(pageSize = 5, prefetchDistance = 5),
        pagingSourceFactory = { MoviePagingSource(service = service) }
    ).flow.cachedIn(viewModelScope)

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