package com.example.taller4_pdm_ca.pojos

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "author_book_table",
    primaryKeys = ["idAuthor", "idBook"],
    foreignKeys = [ForeignKey(
        entity = Author::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idAuthor")
    ), ForeignKey(
        entity = Book::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idBook")
    )]
)

data class AuthorxBook(
    var idAuthor: Int,
    var idBook: Int
)