package com.example.taller4_pdm_ca.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4_pdm_ca.dao.BookDao
import com.example.taller4_pdm_ca.pojos.Book

class BookRepository(private val bookDao: BookDao) {

    val allBooks: LiveData<List<Book>> = bookDao.getAllBooks()
    val favoritesBooks: LiveData<List<Book>> = bookDao.getFavoritesBooks()

    @WorkerThread
    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }

    @WorkerThread
    suspend fun addFavorite(id: Int) {
        bookDao.addFavorite(id)
    }

    @WorkerThread
    suspend fun removeFavorite(id: Int) {
        bookDao.removeFavorite(id)
    }

}