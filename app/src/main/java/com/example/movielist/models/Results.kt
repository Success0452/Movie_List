package com.example.movielist.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

// movie result data class, that serves as a model to handle the response form the network

// serializable annotation that identify the class as a model class for response[ktor]
@Serializable
data class Results(
    // all parameters declared are in alignment with the response data from the network
    val description: String,
    val favorite_count: Int,
    val id: Int,
    val item_count: Int,
    val iso_639_1: String,
    val list_type: String,
    val name: String,
    val poster_path: String?,
)