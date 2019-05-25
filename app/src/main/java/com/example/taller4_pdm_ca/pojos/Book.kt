package com.example.taller4_pdm_ca.pojos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "book_table",
    foreignKeys = [ForeignKey(
        entity = Publisher::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idPublisher")
    )]
)
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "Cover")
    val cover_url: String,
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Edition")
    val edition: String,
    @ColumnInfo(name = "Synopsis")
    val synopsis: String,
    @ColumnInfo(name = "ISBN")
    val isbn: String,
    @ColumnInfo(name = "fav")
    val fav: Boolean,
    @ColumnInfo(name = "idPublisher")
    val idPublisher: Int
)