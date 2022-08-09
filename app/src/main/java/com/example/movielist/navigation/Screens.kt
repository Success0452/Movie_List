package com.example.movielist.navigation

// screen object class to declare names for each variables
sealed class Screens(val route: String) {
    // main page declaration of name
    object getMainPage : Screens(route = "main_page")
}