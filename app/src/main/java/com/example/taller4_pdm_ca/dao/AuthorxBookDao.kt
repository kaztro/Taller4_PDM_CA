package com.example.taller4_pdm_ca.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taller4_pdm_ca.pojos.AuthorxBook

@Dao
interface AuthorxBookDao{

    @Insert
    suspend fun insert(authorxBookDao: AuthorxBookDao)

    @Query("SELECT * FROM author_book_table")
    fun getAllAuthorBooks() : LiveData<List<AuthorxBook>>
}