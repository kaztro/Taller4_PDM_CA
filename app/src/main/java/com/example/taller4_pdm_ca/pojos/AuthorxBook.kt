package com.example.taller4_pdm_ca.pojos

import androidx.room.Entity
import androidx.room.ForeignKey

<<<<<<< HEAD
@Entity(
    tableName = "author_book_table",
    primaryKeys = ["idAuthor", "idBook"],
    foreignKeys = [ForeignKey(
        entity = Author::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idAuthor")
    ), ForeignKey(
=======
@Entity(tableName = "author_book_table",
    primaryKeys = arrayOf("idAuthor", "idBook"),
    foreignKeys = [ForeignKey(entity = Author::class,
        parentColumns = ["id"],
        childColumns = ["idAuthor"]), ForeignKey(
>>>>>>> 2615df79b10a4f8c2fae1826079759708df7eea2
        entity = Book::class,
        parentColumns = ["id"],
        childColumns = ["idBook"]
    )]
)

data class AuthorxBook(
    var idAuthor: Int,
    var idBook: Int
)