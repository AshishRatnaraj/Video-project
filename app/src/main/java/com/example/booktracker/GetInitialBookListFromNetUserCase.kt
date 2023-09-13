package com.example.booktracker

class GetInitialBookListFromNetUserCase {
    private val repo: BooksRepository = BooksRepository()
    private val getSortedBooksCacheCase = GetSortedBooksFromCacheUseCase()
    suspend operator fun invoke(): List<Book>{
        repo.loadBooks()
        return getSortedBooksCacheCase()
    }

}