package com.example.taller4_pdm_ca.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taller4_pdm_ca.pojos.Book

@Dao
interface BookDao {

    @Query("DELETE FROM book_table")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Query("SELECT * FROM book_table")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE fav = 1")
    fun getFavoritesBooks(): LiveData<List<Book>>

    @Query("UPDATE book_table SET fav = 1 WHERE id = :id")
    suspend fun addFavorite(id: Int)

    @Query("UPDATE book_table SET fav = 0 WHERE id = :id")
    suspend fun removeFavorite(id: Int)

}