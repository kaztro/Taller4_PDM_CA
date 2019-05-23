package com.example.taller4_pdm_ca.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taller4_pdm_ca.pojos.Book
import com.example.taller4_pdm_ca.pojos.BookxTags
import com.example.taller4_pdm_ca.pojos.Tags

@Dao
interface BookxTagsDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookxTags: BookxTags)

    @Query("SELECT * FROM book_tags_table")
    fun getAllBookTags() : LiveData<List<BookxTags>>

    @Query("SELECT * FROM tags_table INNER JOIN book_tags_table ON tags_table.id=book_tags_table.idTags WHERE book_tags_table.idBook=:bookId")
    fun getTagsForBook(bookId:Int):LiveData<List<Tags>>

    @Query("SELECT * FROM book_table INNER JOIN book_tags_table ON book_table.id=book_tags_table.idBook WHERE book_tags_table.idTags=:tagId")
    fun getBooksForTag(tagId:Int):LiveData<List<Book>>
}