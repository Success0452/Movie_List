package com.example.movielist.navigation

sealed class Screens(val route: String) {
    object getInitialPage : Screens(route = "initial_page")
    object getMainPage : Screens(route = "main_page")
}