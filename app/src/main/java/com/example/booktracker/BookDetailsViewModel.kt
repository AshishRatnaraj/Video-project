package com.example.booktracker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookDetailsViewModel: ViewModel {
    private var api: BooksApi
    val state = mutableStateOf<Book?>(null)
    private val errorHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
    }
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://booklist-bdb4d-default-rtdb.firebaseio.com/")
            .build()

        api = retrofit.create(BooksApi::class.java)
        getBook(5)
    }
    private fun getBook(id:Int) {
        viewModelScope.launch(errorHandler) {
            val book = getRemoteBook(id)
            state.value = book
        }

    }
    private suspend fun getRemoteBook(id:Int) {
        return withContext(Dispatchers.IO){
            val mapWrapper = api.getBook(id)
            return@withContext mapWrapper.values.first()

        }
    }
}

