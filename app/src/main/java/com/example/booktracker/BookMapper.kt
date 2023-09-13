package com.example.booktracker

import java.util.Locale

fun Book.toRemoteBook(): Book {
    return Book(
        id = id,
        title = title,
        author = author,
        genre = genre,
        series = series,
        )
}
fun RemoteBook.toLocalBook(): LocalBook {
    return LocalBook(
        id = id,
        title = title,
        author = author,
        genre = genre,
        series = series,
    )
}



fun LocalBook.toBook():Book{
    return Book(
        id = id,
        title = title,
        author = author,
        genre = genre,
        series = series
    )
}
fun List<LocalBook>.toBookList(): List<Book> {
    return this.map {
        Book(
            id = it.id,
            title = it.title,
            author = it.author,
            genre = it.genre,
            series = it.series,
            finished = false
        )
    }
}

fun List<RemoteBook>.toLocalBookList(): List<LocalBook>{
    return this.map{
        LocalBook(
            id= it.id,
            title = it.title,
            author = it.author,
            genre = it.genre,
            series =it.series

        )
    }
}



