package com.example.movielist.service.implementation

import com.example.movielist.models.MovieResponse
import com.example.movielist.route.HttpRoute
import com.example.movielist.service.interfaces.ApiService
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

// implementation of the ApiService class that needs to extend the ApiService class to inherite from it
class ApiServiceImpl(
    private val client: HttpClient
    ) : ApiService {

    // override the loadMovies function declared in the ApiService Interface for Implementation
    override suspend fun loadMovie(page :Int, size: Int): MovieResponse? {
        // use of try/catch to ensure error handling
        return try {
            // calling the get request from the HttpCient
            client.get {
                // passing of url
                url(HttpRoute.LIST_MOVIE)
                url{
                    // passing of required parameters, needed by the url
                    parameters.append("api_key", HttpRoute.API_KEY)
                    parameters.append("language", HttpRoute.LANGUAGE)
                    parameters.append("page", page.toString())
                }
            }
            // handling errors from the server
        }catch (e: RedirectResponseException){
            print("Error: ${e.response.status.description}")
            null
            // handling errors from the server
        }catch (e: ServerResponseException){
            print("Error: ${e.response.status.description}")
            null
            // handling errors from the server
        }catch (e: ClientRequestException){
            print("Error: ${e.response.status.description}")
            null
            // handling errors from the server
        }catch (e: Exception){
            print("Error: ${e.message}")
            null
        }
    }
}