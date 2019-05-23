package com.example.taller4_pdm_ca.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taller4_pdm_ca.pojos.Publisher

@Dao
interface PublisherDao{

    @Insert
    suspend fun insert(publisher: Publisher)

    @Query("SELECT * FROM publisher_table")
    fun getAllPublishers(): LiveData<List<Publisher>>
}