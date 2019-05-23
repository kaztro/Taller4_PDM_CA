package com.example.taller4_pdm_ca.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taller4_pdm_ca.pojos.BookxTag

@Dao
interface BookxTagDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookxTag: BookxTag)

    @Query("SELECT * FROM book_tags_table")
    fun getAllBookTags() : LiveData<List<BookxTag>>
}