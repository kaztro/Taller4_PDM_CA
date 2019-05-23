package com.example.taller4_pdm_ca.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taller4_pdm_ca.pojos.BookxPublisher

@Dao
interface BookxPublisherDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookxPublisher: BookxPublisher)

    @Query("SELECT * FROM book_publisher_table")
    fun getAllBookPublisher() : LiveData<List<BookxPublisher>>
}