package com.example.taller4_pdm_ca.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taller4_pdm_ca.pojos.BookxTags

@Dao
interface BookxTagsDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookxTags: BookxTags)

    @Query("SELECT * FROM book_tags_table")
    fun getAllBookTags() : LiveData<List<BookxTags>>
}