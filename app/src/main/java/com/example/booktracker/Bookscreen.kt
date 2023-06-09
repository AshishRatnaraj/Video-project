 package com.example.booktracker

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import.androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.Alignment

@Composable
fun Bookscreen()
    LazyColumn(
        contentPadding = Paddingvalues(
            vertical = 8.dp,
            horizontal = 6.dp
        )
        item(mockBookList[0]){book
            BookItem(Book)
        }
        BookItem(book)

    }
}
    @Composable
fun BookItem(book:Book) {
    Card(

      elevation = 3.dp,
      modifier = Modifier
          .padding(8.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
            .padding(8.dp)
        ){
            BookIcon  (
                Icons.Default.Check ,
                Modifier.weight(0.15f)

            )
            BookDetails(
                title =book.title,
                author = book.author,
                modifier = Modifier.weight(0.85f)
            )
    }

    }
}


@Composable
private fun bookDetails(title: String, author:String, modifier: Modifier) {

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
private fun BookIcon(icon: ImageVector,modifier: Modifier) {
    Image(
        imageVector = icon,
                contentDescription = "Book Icon",
                modifier = Modifier
                .padding(6.dp),
    )
}






data class Book (
    val id: Int,
    val title: String,
    val author: String,
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



