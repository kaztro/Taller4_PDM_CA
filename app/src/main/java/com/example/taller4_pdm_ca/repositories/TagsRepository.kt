package com.example.taller4_pdm_ca.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4_pdm_ca.dao.TagsDao
import com.example.taller4_pdm_ca.pojos.Tags

class TagsRepository(private val tagsDao: TagsDao) {

    val allTags: LiveData<List<Tags>> = tagsDao.getAllTags()

    @WorkerThread
    suspend fun insert(tags: Tags){
        tagsDao.insert(tags)
    }
}