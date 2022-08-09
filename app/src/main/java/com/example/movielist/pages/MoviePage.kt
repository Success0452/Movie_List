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
import com.example.movielist.room.models.Movie
import com.example.movielist.viewmodel.MovieViewModel

enum class TouchState{
    Touched, NotTouched
}

@Composable
fun MovieList(navController: NavController, movieViewModel: MovieViewModel)
{
    val myContext = LocalContext.current
    val localMovieList = movieViewModel.roomdb.collectAsState(initial = emptyList()).value

    val lc = LocalContext.current
    val connectivityManager = lc.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo

    val pager = movieViewModel.pager.collectAsLazyPagingItems()
    Scaffold(backgroundColor = Color.LightGray)
    {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected){
                LazyColumn(content = {
                    item {
                        Header()
                    }
                    items(pager){value ->
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
                    pager.apply {
                        when {
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
                                                )
                                        )
                                    }
                                }
                            }

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
                                                )
                                        )
                                    }
                                }
                            }
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
                                                )
                                        )
                                    }
                                }
                            }
                        }
                    }

                })
            }else{
                Text("You are viewing this page offline")
                if (localMovieList.isEmpty()){
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Connect to your Internet")
                    }
                }
                LazyColumn(content = {
                    item {
                        Header()
                    }
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
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MovieCard(movie: Results){
    var currentState: TouchState by remember{ mutableStateOf(TouchState.NotTouched)}

    var transition = updateTransition(targetState = currentState, label = "animation")

    val scale: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f)}, label = ""
    ) {state ->
        if (state == TouchState.Touched){
            1.3f
        }else{
            1f
        }
    }

    val colorAlpha: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f)}, label = ""
    ) {state ->
        if (state == TouchState.Touched){
            1f
        }else{
            0.2f
        }
    }
    
    Box(
        Modifier
            .fillMaxWidth()
            .height(270.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            Modifier
                .fillMaxWidth()
                .height(270.dp)
                .pointerInteropFilter {
                    currentState = when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            TouchState.Touched
                        }
                        else -> {
                            TouchState.NotTouched
                        }
                    }
                    true
                },
            shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp, topEnd = 5.dp, bottomStart = 5.dp),
            backgroundColor = Color.Red,
            elevation = 6.dp
        ) {
            Row(modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color.Red.copy(0.2f),
                            Color.Red.copy(0.2f),
                            Color.Red.copy(colorAlpha)
                        )
                    )
                )) {

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Id: ${movie.id}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    Text(
                        text = "Movie Name: ${movie.name}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    Text(
                        text = "List Type: ${movie.list_type}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    Text(
                        text = "Language: ${movie.iso_639_1}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    Text(
                        text = "Favourite Count: ${movie.favorite_count.toString()}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())

                    Text(
                        text = "Item Count: ${movie.item_count.toString()}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    Text(
                        text = "Description: \n${movie.description}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height((110 * scale).dp)
                .padding(end = 32.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LocalMovieCard(movie: Movie){
    var currentState: TouchState by remember{ mutableStateOf(TouchState.NotTouched)}

    var transition = updateTransition(targetState = currentState, label = "animation")

    val scale: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f)}, label = ""
    ) {state ->
        if (state == TouchState.Touched){
            1.3f
        }else{
            1f
        }
    }

    val colorAlpha: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f)}, label = ""
    ) {state ->
        if (state == TouchState.Touched){
            1f
        }else{
            0.2f
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .height(270.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            Modifier
                .fillMaxWidth()
                .height(270.dp)
                .pointerInteropFilter {
                    currentState = when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            TouchState.Touched
                        }
                        else -> {
                            TouchState.NotTouched
                        }
                    }
                    true
                },
            shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp, topEnd = 5.dp, bottomStart = 5.dp),
            backgroundColor = Color.Red,
            elevation = 6.dp
        ) {
            Row(modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color.Red.copy(0.2f),
                            Color.Red.copy(0.2f),
                            Color.Red.copy(colorAlpha)
                        )
                    )
                )) {

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Id: ${movie.id}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    Text(
                        text = "Movie Name: ${movie.name}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    Text(
                        text = "List Type: ${movie.list_type}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    Text(
                        text = "Language: ${movie.iso_639_1}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    Text(
                        text = "Favourite Count: ${movie.favorite_count.toString()}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())

                    Text(
                        text = "Item Count: ${movie.item_count.toString()}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    Text(
                        text = "Description: \n${movie.description}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height((110 * scale).dp)
                .padding(end = 32.dp)
                .align(Alignment.BottomEnd)
        )
    }
}