package com.example.taller4_pdm_ca.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4_pdm_ca.dao.BookxTagsDao
import com.example.taller4_pdm_ca.pojos.Book
import com.example.taller4_pdm_ca.pojos.BookxTags
import com.example.taller4_pdm_ca.pojos.Tags

class BookxTagsRepository(private val bookxTagsDao: BookxTagsDao) {
    val allBookxTags: LiveData<List<BookxTags>> = bookxTagsDao.getAllBookTags()

    fun getTagsForBook(bookId: Int): LiveData<List<Tags>> {
        return bookxTagsDao.getTagsForBook(bookId)
    }

    fun getBooksForTag(tagId: Int): LiveData<List<Book>> {
        return bookxTagsDao.getBooksForTag(tagId)
    }

    @WorkerThread
    suspend fun insert(bookxTags: BookxTags) {
        bookxTagsDao.insert(bookxTags)
    }
}