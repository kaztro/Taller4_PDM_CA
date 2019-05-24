package com.example.taller4_pdm_ca.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4_pdm_ca.dao.AuthorxBookDao
import com.example.taller4_pdm_ca.pojos.Author
import com.example.taller4_pdm_ca.pojos.AuthorxBook
import com.example.taller4_pdm_ca.pojos.Book

class AuthorxBookRepository(private val authorxBookDao: AuthorxBookDao) {
    val allAuthorxBooks: LiveData<List<AuthorxBook>> = authorxBookDao.getAllAuthorxBooks()

    fun getAuthorsForBook(bookId: Int): LiveData<List<Author>> {
        return authorxBookDao.getAuthorsForBook(bookId)
    }

    fun getBooksForAuthor(authorId: Int): LiveData<List<Book>> {
        return authorxBookDao.getBooksForAuthor(authorId)
    }

    @WorkerThread
    suspend fun insert(authorxBook: AuthorxBook) {
        authorxBookDao.insert(authorxBook)
    }
}

