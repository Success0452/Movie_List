package com.example.movielist.models

import kotlinx.serialization.Serializable

// movie response data class, that serves as a model to handle the response form the network

// serializable annotation that identify the class as a model class for response[ktor]
@Serializable
data class MovieResponse (
    // all parameters declared are in alignment with the response data from the network
    val id: Int,
    val page: Int,
    val results: List<Results>,
    )