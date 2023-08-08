package com.example.booktracker

import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiii {
    @GET("books.json")
    suspend fun getBooks(): List<Book>

    @GET("books.json?orderBy=\"r_id\"")
    suspend fun getBooks(@Query("equalTo") id:Int): Map<String, Book>
}