package com.example.booktracker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun BookScreen(){
    val viewModel: BooksTrackerViewModel = viewModel()

    LaucnchedEffect(Key1 = "request_Books") {
        viewModel.getBooks()
    }
    LazyColumn(
        contentPadding = PaddingValues(
         vertical = 8.dp,
         horizontal = 6.dp
        )
    ){
        items(viewModel.state.value){
        BookItem(book) { id ->
            viewModel.toogleFinished(id)
        }
        }
    }
class BookTrackerViewModel():  ViewModel() {
     private var api: BooksApi
     private lateinit var booksCall: Call<List<Book>>
     val state = mutableStateOf(emptyList<Book>())


    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://booklist-bdb4d-default-rtdb.firebaseio.com/")
            .build()

      api = retrofit.create(BooksApi::class.java)
      getBooks()
    }


    fun getBooks(){
        booksCall = api.getBooks()
          booksCall.enqueue(
            object :Callback<List<Book>>{
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                response.body()?.let { books -> state.value = books }
            }
            override fun onFailure(call: Call<List<Book>>,t:Throwable){
                t.printStackTrace()
            }
        }

        override fun onCLeared() {
                  super.onCleared()
                  booksCall.cancel()
              }



              fun toggleFinished(id: Int) {
                val books = state.value.toMutableList()
                val bookIndex = books.indexOfFirst {it.id==id }
        val book = (books[bookIndex]).also {
            books[bookIndex] = it.copy( finished = !it.finished)
        }
        state.value = books
        }

