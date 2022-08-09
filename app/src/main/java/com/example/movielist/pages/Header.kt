package com.example.movielist.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movielist.R

@Composable
fun Header(){
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_movie_filter_24),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(end = 32.dp)
                .size(30.dp)
        )
        
        Spacer(modifier = Modifier.height(10.dp))
        
        Text(
            text = "Movie Database Api",
            style =  TextStyle(fontSize = 18.sp),
            color = MaterialTheme.colors.onPrimary
        )

        Text(
            text = "Movie List You can download",
            style =  TextStyle(fontSize = 18.sp),
            color = MaterialTheme.colors.onPrimary
        )

    }
}