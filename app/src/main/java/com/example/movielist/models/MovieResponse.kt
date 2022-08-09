package com.example.movielist.models

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse (
    val id: Int,
    val page: Int,
    val results: List<Results>,
    )