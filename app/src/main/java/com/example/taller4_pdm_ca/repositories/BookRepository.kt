package com.example.taller4_pdm_ca.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4_pdm_ca.dao.BookDao
import com.example.taller4_pdm_ca.pojos.Book

class BookRepository(private val bookDao: BookDao) {

    val allBooks: LiveData<List<Book>> = bookDao.getAllBooks()

    @WorkerThread
    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }

}