package com.example.booktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booktracker.ui.theme.BooktrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooktrackerTheme {
                //BookScreen()
                BookTrackerApp()

            }
        }
    }
}
 @Composable
 private fun BookTrackerApp() {
     val navController = rememberNavController()
     NavHost(navController, startDestination = "") {
         composable(route = "books") {
             BooksScreen()
            navController.navigate("books/$id")
         }

         composable(
             route = "books/{books_id}",
             arguments = listOf(navArgument("book_id") {
                 type = NavType.IntType
             })
         ) {

             BookDetailsScreen()
         }
     }
 }

@Preview(showBackground = true)
@Composable
fun defaultPreview() {
    BooktrackerTheme {
        BooksScreen()
    }
}


