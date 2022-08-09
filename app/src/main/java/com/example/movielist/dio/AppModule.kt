package com.example.movielist.dio

import android.app.Application
import androidx.room.Room
import com.example.movielist.room.database.MovieDatabase
import com.example.movielist.room.interfaces.RepositoryService
import com.example.movielist.room.implementation.RepositoryServiceImpl
import com.example.movielist.service.interfaces.ApiService
import com.example.movielist.service.implementation.ApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun apiService(): ApiService = ApiServiceImpl(
        client = HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature){
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        prettyPrint = true
                        isLenient = true
                        encodeDefaults = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    )

    @Provides
    @Singleton
    fun roomDatabase(app: Application): MovieDatabase {
       return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            MovieDatabase.db_Name
        )
           .fallbackToDestructiveMigration()
           .build()
    }

    @Provides
    @Singleton
    fun repositoryDB(db: MovieDatabase): RepositoryService {
        return RepositoryServiceImpl(
            apiService(),
            db.dbMovieDao()
        )
    }
}