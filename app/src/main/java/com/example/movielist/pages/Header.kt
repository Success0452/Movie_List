package com.example.movielist.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movielist.R
import com.example.movielist.models.Results
import com.example.movielist.viewmodel.MovieViewModel

// header composable function that entails all the design of the header
@Composable
fun Header(pager: LazyPagingItems<Results>){
    // column widget to create a column of items
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        // image widget to created and place a specified image accordingly
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_movie_filter_24),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(end = 32.dp)
                .size(80.dp)
                .clickable {
                   pager.refresh()
                }
        )
        // spacer widget to specify empty spaces
        Spacer(modifier = Modifier.height(10.dp))

        // text widget to allow for writing and displaying of text
        Text(
            text = "Movie Database Api",
            style =  TextStyle(fontSize = 18.sp),
            color = MaterialTheme.colors.onPrimary
        )

        // this is the local screen that gets displayed when there is no network
        if (pager.itemCount == 0){
            Text("You are viewing this page offline")
        }

    }
}
