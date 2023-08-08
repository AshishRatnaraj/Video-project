package com.example.booktracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import org.w3c.dom.Text


@Composable
fun BookDetailsScreen(){
    val viewModel:BookDetailsViewModel =ViewModel()
    val book = viewModel.state.value
    if (book != null){

        Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(16.dp)
        ){
            val icon = if(book.finished) Icons.Default.Check else Icons.Default.Class

            FinishedIcon(
                icon =icon,
                modifier = Modifier.padding(top = 32.dp,bottom = 32),
                onClick = {}
            )
            BookDetails(
                author = book.author,
                title =book.title,
                modifier = Modifier.padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            AdditionalDetails(
                genre = book.genre,
                series = book.series)
        }
    }
        }


@Composable
fun AdditionalDetails(
        genre:String,
        series: String?,
        modifier: Modifier = Modifier.padding(bottom = 32.dp),
        horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally
){
 CompositionLocalProvider(  LocalContentAlpha provides ContentAlpha.medium){
     Text(
          text = genre,
         fontSize = 20.sp
     )
     Text(
         text = series ?:"No Series",
         fontSize = 20.sp
     )
   }
}