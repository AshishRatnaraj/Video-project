 package com.example.booktracker


import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.mediahome.books.BookItem


 @Composable
 fun BooksScreen(onItemClick: (id:Int) -> Unit ={}) {
     val  viewModel: BooksViewModel = viewModel()
     val state =viewModel.state.value


     )
     Box (
         contentAlignment = Alignment.Center,
         modifier = Modifier.fillMaxSize()
     ){
         LazyColumn(
             contentPadding = PaddingValues(
                 vertical = 8.dp,
                 horizontal = 6.dp
             )
         ) {
             items(state.books) { book ->
                 BookItem(
                     book,
                     onFinishedClick = {id -> viewModel.toggleFinished(id)},
                     onItemClick = {id -> onItemClick(id)}
                 )

             }
         }
        if(state.isLoading){
            CircularProgressIndicator()
      }
      if(state.error !=null){
           Text(
               text= state.error,
               fontSize = 30.sp,

      }

     }




     @Composable
     fun BookItem(
         book: Book,
         onFinishedClick: (id: Int) -> Unit,
         onItemClick: (id: Int) -> Unit
     ) {
         val icon = if (book.finished) Icons.Default.Check else Icons.Default.Clear
         Card(

             elevation = 3.dp,
             modifier = Modifier
                 .padding(8.dp)
                 .clickable { onItemClick(book.id) }
         ) {
             Row(
               verticalAlignment = Alignment.CenterVertically,
                 modifier = Modifier
                     .padding(8.dp)
             ) {
                 FinishedIcon(
                     icon,
                     Modifier
                         .weight(0.15f)
                 ){ onFinishedClick(book.id)}
                 BookDetails(
                     title = book.title,
                     author = book.author,
                     modifier = Modifier.weight(0.85f)
                 )
             }
         }
     }

 @Composable
fun BookDetails(title: String,
                author:String,
                modifier: Modifier,
                 horizontalAlignment: Alignment.Horizontal = Alignment.Start
 ) {

    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment
    )
    {
        Text(
            text = title,
            fontSize = 24.sp
        )

        CompositionLocalProvider(
            LocalContentAlpha provides
                    ContentAlpha.medium

        ) {
            Text(
                text = author,
                fontSize = 20.sp
            )

        }
    }
}
@Composable
  fun FinishedIcon(
    icon: ImageVector,
    modifier: Modifier,
    onClick: () -> Unit
){

    Image (
        imageVector = icon,
        contentDescription = "Book Icon",
        modifier = modifier
            .padding(6.dp)
            .clickable { onClick() }


    )
} 








