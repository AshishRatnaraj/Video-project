package com.example.booktracker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BookTrackerViewModel():  ViewModel() {
fun getbooks() =mockBookList
val state = mutableStateOf(mockBookList)
    fun toggleFinished(id: Int)
    {
                val books = state.value.toMutableList()
                val bookIndex = books.indexOfFirst {it.id==id }
        val book = (books[bookIndex]).also {
            books[bookIndex] = it.copy( finished = !it.Finished)
        }
        state.value = books


            }

        }