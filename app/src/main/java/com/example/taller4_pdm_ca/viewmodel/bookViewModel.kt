package com.example.taller4_pdm_ca.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taller4_pdm_ca.pojos.*
import com.example.taller4_pdm_ca.repositories.AuthorRepository
import com.example.taller4_pdm_ca.repositories.BookRepository
import com.example.taller4_pdm_ca.repositories.PublisherRepository
import com.example.taller4_pdm_ca.repositories.TagsRepository
import com.example.taller4_pdm_ca.room.LibraryRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val bookRepository: BookRepository
    //private val tagsRepository: TagsRepository
    //private val authorRepository: AuthorRepository
    //private val publisherRepository: PublisherRepository
    //private val bookxTagsRepository: BookxTagsRepository
    //private val authorxBookRepository: AuthorxBookRepository
    val allBooks: LiveData<List<Book>>
    val favBooks: LiveData<List<Book>>
    //val allTags: LiveData<List<Tags>>
    //val allAuthor: LiveData<List<Author>>
    //val allPublisher: LiveData<List<Publisher>>
    //val allBookxTags: LiveData<List<BookxTags>>
    //val allAuthorxBook: LiveData<List<AuthorxBook>>

    init {
        val booksDao = LibraryRoomDatabase.getDatabase(application,viewModelScope).bookDao()
        bookRepository = BookRepository(booksDao)

        /*
        val tagssDao = LibraryRoomDatabase.getDatabase(application,viewModelScope).tagsDao()

        tagsRepository = TagsRepository(tagssDao)

        val authorsDao = LibraryRoomDatabase.getDatabase(application,viewModelScope).authorDao()
        authorRepository = AuthorRepository(authorsDao)

        val publishersDao = LibraryRoomDatabase.getDatabase(application,viewModelScope).publisherDao()
        publisherRepository = PublisherRepository(publishersDao)
        */


        allBooks = bookRepository.allBooks
        favBooks = bookRepository.favoritesBooks

        /*
        allTags = tagsRepository.allTags
        allAuthor = authorRepository.allAuthors
        allPublisher = publisherRepository.allPublishers
        */

    }

    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.insert(book)
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
//Aaah! Las credenciales
// : v