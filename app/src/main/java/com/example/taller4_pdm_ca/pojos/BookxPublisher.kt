package com.example.taller4_pdm_ca.pojos

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "book_publisher_table",
    primaryKeys = ["idBook", "idPublisher"],
    foreignKeys = [ForeignKey(entity = Book::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idBook")), ForeignKey(
        entity = Publisher::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idPublisher")
    )]
)

data class BookxPublisher(
    var idBook: Int,
    var idPublisher: Int
)