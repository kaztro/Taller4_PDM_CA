package com.example.taller4_pdm_ca.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taller4_pdm_ca.pojos.Tags

@Dao
interface TagsDao{

    @Insert
    suspend fun insert(tags: Tags)

    @Query("SELECT * FROM tags_table")
    fun getAllTags() : LiveData<List<Tags>>
}