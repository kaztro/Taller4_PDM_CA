package com.example.taller4_pdm_ca.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taller4_pdm_ca.pojos.Book

@Dao
interface BookDao {

    //@Query("DELETE FROM book_table")
    //fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Query("SELECT * FROM book_table")
    fun getAllBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE fav = 1")
    fun getFavoritesBooks() : LiveData<List<Book>>
}