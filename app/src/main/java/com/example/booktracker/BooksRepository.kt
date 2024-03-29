package com.example.booktracker

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException


class BooksRepository {

    private var api: BooksApi = Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .baseUrl("https://booklist-bdb4d-default-rtdb.firebaseio.com/")
        .build()
        .create(BooksApi::class.java)
    private var booksDao = BooksDb.getDaoInstance(BookApplication.getAppContext())

     suspend fun loadBooks() {
        return withContext(Dispatchers.IO) {
            try {
                 refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        Log.e("BooksViewModel", "Error: No data to firebase")
                        if (booksDao.getAll().isEmpty()) {
                            Log.e("BooksViewModel", "Error: No data to firebase")
                            throw Exception("Error: Device offline and\nno data from local cache")
                        }

                    }else -> throw e
                }
            }

        }
    }

     suspend fun getCachedBooks():List<Book>{
         return withContext(Dispatchers.IO){
             return@withContext booksDao.getAll().toBookList()




         }
     }

    private suspend fun refreshCache() {
        val remoteBooks = api.getBooks()
        val finishedBooks = booksDao.getAllFinished()
        booksDao.addAll(remoteBooks.toLocalBookList())
        booksDao.updateAll(
            finishedBooks.map{
                PartialBookLocal_finished(it.id,true)
            }
        )
    }
     suspend fun toggleFinishedDb(id: Int, value: Boolean) =
        withContext(Dispatchers.IO) {
            booksDao.update(
                PartialBookLocal_finished(
                    id = id,
                    finished = value
                )
            )
            booksDao.getAll().sortedBy{it. title}
        }
}


