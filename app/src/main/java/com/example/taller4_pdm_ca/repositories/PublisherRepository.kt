package com.example.taller4_pdm_ca.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4_pdm_ca.dao.PublisherDao
import com.example.taller4_pdm_ca.pojos.Publisher

class PublisherRepository(private val publisherDao: PublisherDao) {

    val allPublishers:LiveData<List<Publisher>> = publisherDao.getAllPublishers()

    @WorkerThread
    suspend fun insert(publisher: Publisher){
        publisherDao.insert(publisher)
    }
}