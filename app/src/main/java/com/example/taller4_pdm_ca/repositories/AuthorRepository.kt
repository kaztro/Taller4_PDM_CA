package com.example.taller4_pdm_ca.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4_pdm_ca.dao.AuthorDao
import com.example.taller4_pdm_ca.pojos.Author

class AuthorRepository(private val authorDao: AuthorDao) {
    val allAuthors: LiveData<List<Author>> = authorDao.getAllAuthors()

    @WorkerThread
    suspend fun insert(author: Author){
        authorDao.insert(author)
    }
}