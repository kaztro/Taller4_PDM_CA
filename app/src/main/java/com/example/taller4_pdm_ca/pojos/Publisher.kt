package com.example.taller4_pdm_ca.pojos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publisher_table")
data class Publisher(
<<<<<<< HEAD
    @PrimaryKey val id : Int,
=======
    @PrimaryKey//(autoGenerate = true)
    val id : Int,
>>>>>>> 2615df79b10a4f8c2fae1826079759708df7eea2
    @ColumnInfo(name = "Name")
    val name : String
)