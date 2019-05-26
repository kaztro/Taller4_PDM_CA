package com.example.taller4_pdm_ca.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taller4_pdm_ca.pojos.Author
import com.example.taller4_pdm_ca.pojos.AuthorxBook
import com.example.taller4_pdm_ca.pojos.Book

@Dao
interface AuthorxBookDao{

    @Insert
    suspend fun insert(authorxBook: AuthorxBook)

    @Query("SELECT * FROM author_book_table")
    fun getAllAuthorxBooks() : LiveData<List<AuthorxBook>>

    @Query("SELECT * FROM author_table INNER JOIN author_book_table ON author_table.id=author_book_table.idAuthor WHERE author_book_table.idBook=:bookId")
    fun getAuthorsForBook(bookId:Int):LiveData<List<Author>>

    @Query("SELECT * FROM book_table INNER JOIN author_book_table ON book_table.id=author_book_table.idBook WHERE author_book_table.idAuthor=:authorId")
    fun getBooksForAuthor(authorId:Int):LiveData<List<Book>>
}