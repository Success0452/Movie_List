package com.example.movielist.ui.theme

import android.content.Context
import android.net.ConnectivityManager
import android.view.KeyEvent.ACTION_DOWN
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.movielist.R
import com.example.movielist.models.Results
import com.example.movielist.pages.Header
import com.example.movielist.pages.LocalMovieCard
import com.example.movielist.pages.MovieCard
import com.example.movielist.room.models.Movie
import com.example.movielist.viewmodel.MovieViewModel

// enum class to track touch state for an animated
enum class TouchState{
    Touched, NotTouched
}

// movie list composable function that houses the widget to draw the ui for listing the movie List in the database
@Composable
fun MovieList(navController: NavController, movieViewModel: MovieViewModel)
{
    // local context to execute context based functions
    val myContext = LocalContext.current

    // retrieving list of movies from the roomdatabse in the viewmodel
    val localMovieList = movieViewModel.roomdb.collectAsState(initial = emptyList()).value

    // checks for internet connectivity status
    val connectivityManager = myContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo

    // retrieving list of movies from the pager in the viewmodel
    val pager = movieViewModel.pager.collectAsLazyPagingItems()
    // scaffold widget to handle other widget for easy arrangment
    Scaffold(
        backgroundColor = Color.LightGray,
        content = {
            // column widget that list items in a vertical manner
            Column(
                // modifier that gives attribute to the specified widget
                Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                // align the vertical arrangement from the top
                verticalArrangement = Arrangement.Top,
                // align the horizontal arrangement from the center
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // if statement to check for the activeness of the network connection
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected){
                    // lazyColumn widget that loads item in a lazy manner
                    LazyColumn(content = {
                        item {
                            Header(pager)
                        }
                        // items that accepts list of the items you want to lazyly display
                        items(pager){value ->
                            // this widget gets repeated in how many times of the item specified
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                MovieCard(value!!)
                                movieViewModel.saveMovie(value)
                            }
                        }
                        // tracking of pager progress to display a loading progress at each stage
                        pager.apply {
                            when {
                                // at refresh stage the circularProgressIndicator appears
                                loadState.refresh is LoadState.Loading -> {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(16.dp)
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier
                                                    .padding(12.dp)
                                                    .align(
                                                        Alignment.Center
                                                    ),
                                                color = Color.Red,
                                                strokeWidth = 5.dp
                                            )
                                        }
                                    }
                                }
                                // at append stage the circularProgressIndicator appears
                                loadState.append is LoadState.Loading -> {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp)
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier
                                                    .padding(12.dp)
                                                    .align(
                                                        Alignment.Center
                                                    ),
                                                color = Color.Red,
                                                strokeWidth = 5.dp
                                            )
                                        }
                                    }
                                }
                                // at prepend stage the circularProgressIndicator appears
                                loadState.prepend is LoadState.Loading -> {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp)
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier
                                                    .padding(12.dp)
                                                    .align(
                                                        Alignment.Center
                                                    ),
                                                color = Color.Red,
                                                strokeWidth = 5.dp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    })
                }else{
                    // gets items from the roomdatabase and not the network directly
                    if (localMovieList.isEmpty()){
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Connect to your Internet")
                        }
                    }
                    // lazyColumn widget that loads item in a lazy manner
                    LazyColumn(content = {
                        item {
                            Header(pager)
                        }
                        // tracking of pager progress to display a loading progress at each stage
                        pager.apply {
                            when {
                                // at refresh stage the circularProgressIndicator appears
                                loadState.refresh is LoadState.Loading -> {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(16.dp)
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier
                                                    .padding(12.dp)
                                                    .align(
                                                        Alignment.Center
                                                    ),
                                                color = Color.Red,
                                                strokeWidth = 5.dp
                                            )
                                        }
                                    }
                                }
                                // at append stage the circularProgressIndicator appears
                                loadState.append is LoadState.Loading -> {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp)
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier
                                                    .padding(12.dp)
                                                    .align(
                                                        Alignment.Center
                                                    ),
                                                color = Color.Red,
                                                strokeWidth = 5.dp
                                            )
                                        }
                                    }
                                }
                                // at prepend stage the circularProgressIndicator appears
                                loadState.prepend is LoadState.Loading -> {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp)
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier
                                                    .padding(12.dp)
                                                    .align(
                                                        Alignment.Center
                                                    ),
                                                color = Color.Red,
                                                strokeWidth = 5.dp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        // items that accepts list of the items you want to lazyly display
                        items(localMovieList){value ->
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                LocalMovieCard(value)
                            }
                        }
                    })
                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { pager.refresh() }, backgroundColor = Color.Red) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "", tint = Color.White)
            }
        }
    )
}

