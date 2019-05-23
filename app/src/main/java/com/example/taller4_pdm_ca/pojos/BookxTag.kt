package com.example.taller4_pdm_ca.pojos

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "book_tags_table",
    primaryKeys = ["idBook", "idTags"],
    foreignKeys = [ForeignKey(entity = Book::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idBook")), ForeignKey(
        entity = Tag::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idTags")
    )]
)

data class BookxTag(
    var idBook: Int,
    var idTag: Int
)