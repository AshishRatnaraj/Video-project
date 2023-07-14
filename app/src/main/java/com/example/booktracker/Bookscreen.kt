 package com.example.booktracker


import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
fun TextInput(){
       val textInputState: MutableState<String> =remember{mutableStateOf("")}
     TextField(
         value= textInputState.value,
         onValueChange = { newValue ->
             textInputState.value = newValue
         }
           label = {
               Text( "Input")
         }
     )

   }
 @Composable
fun BookScreen() {
     val viewModel: BookTrackerViewModel = viewModel()
     val state:MutableState<List<Book>> = remember { mutableStateOf(viewModel.getBooks()) }
     LazyColumn(
         contentPadding = PaddingValues(
             vertical = 8.dp,
             horizontal = 6.dp
         )
     ) {
         items(viewModel.state.value()) { book ->
             BookItem(book{id ->
                 viewmodel.toggle.finished(id)
             }
         }
     }




     @Composable
     fun BookItem(
         book: Book,
         onClick: (id: Int) -> Unit

     ) {

         var readState by remember { mutableStateOf(false) }
         val icon = if (book.finished) Icons.Default.Check else Icons.Default.Clear
         Card(

             elevation = 3.dp,
             modifier = Modifier
                 .padding(8.dp)
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
                 ){ onClick(book.id)}
                 BookDetails(
                     title = book.title,
                     author = book.author,
                     modifier = Modifier.weight(0.85f)
                 )
             }
         }
     }
 }
 @Composable
private fun BookDetails(title: String, author:String, modifier: Modifier) {

    Column(modifier = modifier)
    {
        Text(
            text = title,
            fontSize = 24.sp
        )
        Text(
            text = author,
            fontSize = 24.sp,
            fontWeight = SemiBold,
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
 private fun FinishedIcon(
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

data class Book (
    val id: Int,
    val title: String,
    val author: String,
    val Finished: Boolean = false
)


 val mockBookList = listOf(
    Book(0,"The Fellowship of the Rings","J.R.R Tolkien"),
    Book(1,"The Two Towers","J.R.R Tolkien"),
    Book(2,"The Return of the Kings","J.R.R Tolkien" ),
    Book(3,"The Hobbit","J.R.R Tolkien"),
    Book(4,"The Eye of the world","Robert Jordon"),
    Book(5,"A Christmas Carol","Charles Dickens"),
    Book(6,"A Tale of two Cities","Leo Tolstoy"),
    Book(7,"The Dragon Reborn","Robert Jordon"),
    Book(8,"A Christmas Carol","Charles Dickens"),
    Book(9,"A Tale of the Two Cities","Leo Tolstoy"),
    Book(10,"Moby Dick","Leo Tolstoy"),
    Book(11,"Tom Sawyer","Mark Twain"),
    Book(12,"Alice in the Wonderland","Lewis Carroll"),
    Book(13,"Jene Eyre","Charles Bronte"),
    Book(14,"David Copperfield","Charles Dickens"),
    Book(15,"Getting There","Samir  deokuliar"),
    )



