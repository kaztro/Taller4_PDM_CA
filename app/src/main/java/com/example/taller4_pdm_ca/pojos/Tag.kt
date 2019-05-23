package com.example.taller4_pdm_ca.pojos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags_table")
data class Tag (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "tag_list")
    val tag : String
)