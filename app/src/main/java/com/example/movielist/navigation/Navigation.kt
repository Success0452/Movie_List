package com.example.movielist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movielist.ui.theme.MovieList
import com.example.movielist.viewmodel.MovieViewModel

//composable function that handles the navigation process
@Composable()
fun Navigation(movieViewModel: MovieViewModel){
    // navController to get the controller to be remembered
    val navController = rememberNavController()

    //navHost to handle the routes declared correctly within
    NavHost(navController = navController, startDestination = Screens.getMainPage.route){
        // composable that allow for the connection of views to the navHost
        composable(Screens.getMainPage.route){ MovieList(navController = navController, movieViewModel = movieViewModel) }
    }
}