package com.example.booktracker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.w3c.dom.Text


@Composable
fun BookDetailsScreen(){
    val viewModel:BookDetailsViewModel = viewModel()
    val state = viewModel.state.value


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ){
            if (state.book != null)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                val icon = if (state.book.finished) Icons.Default.Check else Icons.Default.Clear

                FinishedIcon(
                    icon = icon,
                    modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
                    onClick = {}
                )
                BookDetails(
                    author = state.book.author,
                    title = state.book.title,
                    modifier = Modifier.padding(bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                AdditionalDetails(
                    genre = state.book.genre,
                    series = state.book.series
                )
            }
        }

    }
    if(state.isLoading){
        CircularProgressIndicator()
}
  if (state.error!=null){
          Text(
              text = state.error,
              fonsize = 30.0
          )
}

@Composable
fun BookDetails(author: Any, title: Any, modifier: Modifier, horizontalAlignment: Alignment.Horizontal) {

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