package com.example.movielist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movielist.ui.theme.MovieList
import com.example.movielist.viewmodel.MovieViewModel

@Composable()
fun Navigation(movieViewModel: MovieViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.getMainPage.route){
        composable(Screens.getMainPage.route){ MovieList(navController = navController, movieViewModel = movieViewModel) }
    }
}