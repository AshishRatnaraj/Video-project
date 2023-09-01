package com.example.booktracker

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException

class BookDetailsViewModel(private val stateHandle: SavedStateHandle): ViewModel() {
    private val repo = BookDetailsRepository()

    val state: State<BookDetailsScreenState>
        get() = _state
    private val _state = mutableStateOf(
        BookDetailsScreenState(
            book = null,
            isLoading = true
        )
    )


    private val errorHandler = CoroutineExceptionHandler() { _, e ->
        e.printStackTrace()
    }

    init {
        val id = stateHandle.get<Int>("book_id") ?: 0
        getBook(id)
    }

    private fun getBook(id: Int) {
        viewModelScope.launch(errorHandler) {
            val book = repo.getSingleBook(id)
            _state.value = _state.value.copy(
            book = book,
            isLoading = false)
        }
    }
}
