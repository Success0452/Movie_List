package com.example.movielist.service.implementation

import com.example.movielist.models.MovieResponse
import com.example.movielist.route.HttpRoute
import com.example.movielist.service.interfaces.ApiService
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class ApiServiceImpl(
    private val client: HttpClient
    ) : ApiService {

    override suspend fun loadMovie(page :Int, size: Int): MovieResponse? {
        return try {
            client.get {
                url(HttpRoute.LIST_MOVIE)
                url{
                    parameters.append("api_key", HttpRoute.API_KEY)
                    parameters.append("language", HttpRoute.LANGUAGE)
                    parameters.append("page", page.toString())
                }
            }
        }catch (e: RedirectResponseException){
            print("Error: ${e.response.status.description}")
            null
        }catch (e: ServerResponseException){
            print("Error: ${e.response.status.description}")
            null
        }catch (e: ClientRequestException){
            print("Error: ${e.response.status.description}")
            null
        }catch (e: Exception){
            print("Error: ${e.message}")
            null
        }
    }
}