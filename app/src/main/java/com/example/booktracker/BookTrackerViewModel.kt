package com.example.booktracker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class BookTrackerViewModel(private val statehandle: SavedStateHandle):  ViewModel() {
    private var api: BooksApi
     val state = mutableStateOf(emptyList<Book>())
    private val errorHandler = CoroutineExceptionHandler{_, e ->
        e.printStackTrace()
    }


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


    fun getBooks() {
        viewModelScope.launch(errorHandler) {
            val books = getRemoteBooks()
            state.value = books.restoreFinishedField()
        }
    }
    private suspend fun getRemoteBooks(): List<Book>{
        return withContext(Dispatchers.IO) {
            api.getBooks()
        }
    }

    private fun List<Book>.restoreFinishedField(): List<Book> {
        statehandle.get<List<Int>?>("finished")?.let { selectedIds ->
            val booksMap = this.associateBy { it.id }
            selectedIds.forEach { id ->
                booksMap[id]?.finished = true
            }
            return booksMap.values.toList()
        }
        return this
    }




    fun toggleFinished(id: Int) {
                val books = state.value.toMutableList()
                val bookIndex = books.indexOfFirst {it.id==id }
               val book = (books[bookIndex]).also {
            books[bookIndex] = it.copy( finished = !it.finished)
        }
        state.value = books
        }
}

