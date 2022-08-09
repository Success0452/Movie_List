package com.example.movielist.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movielist.models.Results
import com.example.movielist.room.interfaces.MovieDao
import com.example.movielist.room.models.Movie

@Database(entities = [Movie::class], exportSchema = false, version = 6)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun dbMovieDao(): MovieDao

    companion object{
        val db_Name = "movieDB"
    }
}