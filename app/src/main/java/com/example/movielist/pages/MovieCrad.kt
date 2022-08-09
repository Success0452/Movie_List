package com.example.movielist.pages

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movielist.R
import com.example.movielist.models.Results
import com.example.movielist.room.models.Movie
import com.example.movielist.ui.theme.TouchState

// Movie Card composable class
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MovieCard(movie: Results){
    var currentState: TouchState by remember{ mutableStateOf(TouchState.NotTouched) }

    var transition = updateTransition(targetState = currentState, label = "animation")

    val scale: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) }, label = ""
    ) {state ->
        if (state == TouchState.Touched){
            1.3f
        }else{
            1f
        }
    }

    val colorAlpha: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) }, label = ""
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

// Movie Card composable class
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LocalMovieCard(movie: Movie){
    // variable that remember the touch state
    var currentState: TouchState by remember{ mutableStateOf(TouchState.NotTouched) }

    // variable that remember the transition state
    var transition = updateTransition(targetState = currentState, label = "animation")

    // variable that tracks the transition state
    val scale: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) }, label = ""
    ) {state ->
        if (state == TouchState.Touched){
            1.3f
        }else{
            1f
        }
    }

    // variable that tracks the colorAlpha state
    val colorAlpha: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) }, label = ""
    ) {state ->
        if (state == TouchState.Touched){
            1f
        }else{
            0.2f
        }
    }

    // box widget that draws box and the items placed inside
    Box(
        Modifier
            .fillMaxWidth()
            .height(270.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // card widget to create card
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
            // row widget that displays items in horizontal manner
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
                // column widget that displays items in vertical manner
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    // text widget that create text on the screen
                    Text(
                        text = "Id: ${movie.id}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    // spacer widget to create empty space
                    Spacer(modifier = Modifier.height(5.dp))
                    //  divider widget that draws a straight line horizontally
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    // text widget that create text on the screen
                    Text(
                        text = "Movie Name: ${movie.name}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    // spacer widget to create empty space
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    // text widget that create text on the screen
                    Text(
                        text = "List Type: ${movie.list_type}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    // spacer widget to create empty space
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    // text widget that create text on the screen
                    Text(
                        text = "Language: ${movie.iso_639_1}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    // spacer widget to create empty space
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    // text widget that create text on the screen
                    Text(
                        text = "Favourite Count: ${movie.favorite_count.toString()}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    // spacer widget to create empty space
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    // text widget that create text on the screen
                    Text(
                        text = "Item Count: ${movie.item_count.toString()}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth())
                    // text widget that create text on the screen
                    Text(
                        text = "Description: \n${movie.description}",
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    // spacer widget to create empty space
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
        // image widget that draw image on the screen
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