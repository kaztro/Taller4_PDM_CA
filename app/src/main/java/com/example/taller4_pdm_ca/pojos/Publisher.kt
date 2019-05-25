package com.example.taller4_pdm_ca.pojos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publisher_table")
data class Publisher(
    @PrimaryKey//(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "Name")
    val name : String
)