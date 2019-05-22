package com.example.taller4_pdm_ca.pojos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey
    val isbn : String,
    @ColumnInfo(name = "Cover")
    val cover_url : String,
    @ColumnInfo(name = "Title")
    val title : String,
    @ColumnInfo(name = "Edition")
    val edition : String,
    @ColumnInfo(name = "Synopsis")
    val synopsis : String
)