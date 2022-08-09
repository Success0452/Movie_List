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

// object appModule class that serves as the Injection class

// annotation that ensures this class as an injection class
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // provides annotation that ensures this function is been injected to the specified Interface
    @Provides
    fun apiService(): ApiService = ApiServiceImpl(
        // implementation of Http client declared inside the ApiServiceImplementation class
        client = HttpClient(Android){
            // install to ensure logging of the api responses and error
            install(Logging){
                level = LogLevel.ALL
            }
            // install to ensure json features and associated features works correctly
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

    // provides annotation that ensures this function is been injected to the specified Interface
    @Provides
    @Singleton
    fun roomDatabase(app: Application): MovieDatabase {
        // implementation of room integration associated with the movie database class
       return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            MovieDatabase.db_Name
        )
           .fallbackToDestructiveMigration()
           .build()
    }

    // provides annotation that ensures this function is been injected to the specified Interface
    @Provides
    @Singleton
    fun repositoryDB(db: MovieDatabase): RepositoryService {
        // implementation of the Dao declared inside the RepositoryServiceImplementation class
        return RepositoryServiceImpl(
            db.dbMovieDao()
        )
    }
}