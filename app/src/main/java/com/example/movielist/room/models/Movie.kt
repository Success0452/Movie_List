package com.example.movielist.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// model class to store data into the database

//entity annotation to assign a name to your table
@Entity(tableName = "movie_list")

data class Movie(
    val description: String,
    val favorite_count: Int,
    val id: Int,
    val item_count: Int,
    val iso_639_1: String,
    val list_type: String,
    val name: String,
    val poster_path: String? = null,
    // primary key to ensure all items can be tracked
    @PrimaryKey(autoGenerate = true)
    val userId: Int? = null
)
