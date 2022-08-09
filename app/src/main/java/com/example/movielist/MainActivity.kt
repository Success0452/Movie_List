package com.example.movielist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movielist.navigation.Navigation
import com.example.movielist.ui.theme.MovieListTheme
import com.example.movielist.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // handle the implementation of splashscreen
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                // monitor the loading variable to know when the splash screen disappear
                movieViewModel.loading.value
            }
        }
        setContent {
            MovieListTheme {
                // calling the navigation function that handles the routes process
                Navigation(movieViewModel)
            }
        }
    }
}
