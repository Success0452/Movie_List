package com.example.movielist.route

// object class to declare route for easy accessibilty
object HttpRoute {

    // baseUrl for the project been assigned to a private variable
    private const val BASE_URL = "https://api.themoviedb.org/3/movie"
    // concatenation of the baseUrl with the endpoint
    const val LIST_MOVIE = "$BASE_URL/12/lists"
    // api_key been assigned a variable
    const val API_KEY = "5ef9d3ec9eee9711d0353cb8d66d22de"
    // language been assigned a variable
    const val LANGUAGE = "en-US"
}