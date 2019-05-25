package com.example.taller4_pdm_ca.pojos

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "author_book_table",
    primaryKeys = arrayOf("idAuthor", "idBook"),
    foreignKeys = [ForeignKey(entity = Author::class,
        parentColumns = ["id"],
        childColumns = ["idAuthor"]), ForeignKey(
        entity = Book::class,
        parentColumns = ["id"],
        childColumns = ["idBook"]
    )]
)

data class AuthorxBook(
    var idAuthor: Int,
    var idBook: Int
)