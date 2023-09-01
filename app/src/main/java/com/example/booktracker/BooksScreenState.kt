package com.example.booktracker

import com.example.booktracker.Book

data class BooksScreenState (
     val books: List<Book>,
     val isLoading: Boolean,
     val error: String? = null,
     )
