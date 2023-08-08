package com.example.booktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.booktracker.ui.theme.BooktrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooktrackerTheme {
                //BookScreen()
            BookDetailsScreen()

            }
        }
    }


    private var api: BooksApi
    val state = mutableStateOf(emptyList<Book>())


    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://booklist-bdb4d-default-rtdb.firebaseio.com/")
            .build()

        api = retrofit.create(BooksApi::class.java)
    }

    fun getBooks(){
        api.getBooks().execute().body()?.let  {books-> state.value = books }
        override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
            response.body()?.let { books -> state.value = books }
        }
        override fun onFailure(call: Call<List<Book>>,t:Throwable){
            t.printStackTrace()
        }
    }
    override fun onClear()  {
        super. onCleared()
        booksCall.cancel()
    }

    fun toggleFinished(id: Int)
    {
        val books = state.value.toMutableList()
        val bookIndex = books.indexOfFirst {it.id==id }
        val book = (books[bookIndex]).also {
            books[bookIndex] = it.copy( finished = !it.finished)
        }
        state.value = books


    }

}}



