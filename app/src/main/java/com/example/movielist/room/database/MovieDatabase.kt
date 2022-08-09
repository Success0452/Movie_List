package com.example.movielist.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movielist.models.Results
import com.example.movielist.room.interfaces.MovieDao
import com.example.movielist.room.models.Movie

// Database class

// database annotation gets the movie data class and other parameters to allow connection to the database
@Database(entities = [Movie::class], exportSchema = false, version = 6)
abstract class MovieDatabase : RoomDatabase() {
    // abstract declaration of dbMovieDao, that returns MovieDao interface
    abstract fun dbMovieDao(): MovieDao

    // companion object that allow the database name to be called anywher in the codebase
    companion object{
        // storing database name into a variable
        val db_Name = "movieDB"
    }
}