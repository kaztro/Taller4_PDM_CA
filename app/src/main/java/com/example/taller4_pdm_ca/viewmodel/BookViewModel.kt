package com.example.taller4_pdm_ca.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taller4_pdm_ca.pojos.*
import com.example.taller4_pdm_ca.repositories.BookRepository
import com.example.taller4_pdm_ca.room.LibraryRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val bookRepository: BookRepository
    val allBooks: LiveData<List<Book>>
    val favBooks: LiveData<List<Book>>
    //val allTags: LiveData<List<Tags>>
    //val allAuthor: LiveData<List<Author>>
    //val allPublisher: LiveData<List<Publisher>>
    //val allBookxTags: LiveData<List<BookxTags>>
    //val allAuthorxBook: LiveData<List<AuthorxBook>>

    init {
        val bookDao = LibraryRoomDatabase.getDatabase(application,viewModelScope).bookDao()
        val authorDao = LibraryRoomDatabase.getDatabase(application,viewModelScope).authorDao()
        val tagsDao = LibraryRoomDatabase.getDatabase(application,viewModelScope).tagsDao()
        val publisherDao = LibraryRoomDatabase.getDatabase(application,viewModelScope).publisherDao()
        val authorXBookDao = LibraryRoomDatabase.getDatabase(application, viewModelScope).authorXBookDao()
        val bookXTagsDao = LibraryRoomDatabase.getDatabase(application, viewModelScope).bookXTagsDao()
        bookRepository = BookRepository(bookDao,authorDao,tagsDao,publisherDao,authorXBookDao,bookXTagsDao)

        allBooks = bookRepository.allBooks
        favBooks = bookRepository.favoritesBooks

        /*
        allTags = tagsRepository.allTags
        allAuthor = authorRepository.allAuthors
        allPublisher = publisherRepository.allPublishers
        */

    }

    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.insertBook(book)
    }

    fun getAll():LiveData<List<Book>> = allBooks
    fun getFavoritesBooks():LiveData<List<Book>> = favBooks

    /*
    fun insert(author: Author) = viewModelScope.launch(Dispatchers.IO) {
        authorRepository.insert(author)
    }
    fun insert(tags: Tags) = viewModelScope.launch(Dispatchers.IO) {
        tagsRepository.insert(tags)
    }
    fun insert(publisher: Publisher) = viewModelScope.launch(Dispatchers.IO) {
        publisherRepository.insert(publisher)
    }
    */

}