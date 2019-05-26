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
    val allTags: LiveData<List<Tags>>
    val allAuthors: LiveData<List<Author>>
    val allPublishers: LiveData<List<Publisher>>
    val allBookxTags: LiveData<List<BookxTags>>
    val allAuthorxBook: LiveData<List<AuthorxBook>>

    init {
        val bookDao = LibraryRoomDatabase.getDatabase(application, viewModelScope).bookDao()
        val authorDao = LibraryRoomDatabase.getDatabase(application, viewModelScope).authorDao()
        val tagsDao = LibraryRoomDatabase.getDatabase(application, viewModelScope).tagsDao()
        val publisherDao = LibraryRoomDatabase.getDatabase(application, viewModelScope).publisherDao()
        val authorXBookDao = LibraryRoomDatabase.getDatabase(application, viewModelScope).authorXBookDao()
        val bookXTagsDao = LibraryRoomDatabase.getDatabase(application, viewModelScope).bookXTagsDao()
        bookRepository = BookRepository(bookDao, authorDao, tagsDao, publisherDao, authorXBookDao, bookXTagsDao)

        allBooks = bookRepository.allBooks
        favBooks = bookRepository.favoritesBooks
        allAuthors = bookRepository.allAuthors
        allPublishers = bookRepository.allPublishers
        allTags = bookRepository.allTags
        allBookxTags = bookRepository.allBookxTags
        allAuthorxBook = bookRepository.allAuthorxBooks
    }

    fun insertBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.insertBook(book)
    }

    fun insertAuthor(author: Author) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.insertAuthor(author)
    }

    fun insertPublisher(publisher: Publisher) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.insertPublisher(publisher)
    }

    fun insertTag(tag: Tags) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.insertTags(tag)
    }

    fun insertAuthorxBook(authorxBook: AuthorxBook) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.insertAuthorxBook(authorxBook)
    }

    fun insertBookxTags(bookxTags: BookxTags) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.insertBookxTags(bookxTags)
    }

    fun getBooksForAuthor(authorId: Int): LiveData<List<Book>> = bookRepository.getBooksForAuthor(authorId)

    fun getAuthorsForBook(bookId: Int): LiveData<List<Author>> = bookRepository.getAuthorsForBook(bookId)

    fun getBooksForTag(tagId: Int): LiveData<List<Book>> = bookRepository.getBooksForTag(tagId)

    fun getTagsForBook(bookId: Int): LiveData<List<Tags>> = bookRepository.getTagsForBook(bookId)

    fun addFavorite(idBook: Int) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.addFavorite(idBook)
    }

    fun removeFavorite(idBook: Int) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.removeFavorite(idBook)
    }

    fun insertListAuthor(authors: ArrayList<String>, book: Book) {
        var authorList = allAuthors.value
        if (authorList != null) {
            var idAuthor: Int
            if (authorList.isNotEmpty()) idAuthor =
                authorList.get(authorList.indexOf(authorList.get(authorList.size - 1))).id
            else idAuthor = 0
            for (i: Int in 1..authors.size) {
                var author = Author(idAuthor + i, authors[i - 1])
                if (idAuthor != 0) {
                    if (!(isAuthorOnList(author.name_author, authorList))) {
                        insertAuthor(author)
                        insertAuthorxBook(AuthorxBook(book.id, author.id))
                    }
                }
            }
        }
    }

    fun insertListTag(tags: ArrayList<String>, book: Book) {
        var tagList = allTags.value
        if (tagList != null) {
            var idTag: Int
            if (tagList.isNotEmpty()) idTag = tagList.get(tagList.indexOf(tagList.get(tagList.size - 1))).id
            else idTag = 0
            for (i: Int in 1..tags.size) {
                var tag = Tags(idTag + i, tags[i - 1])
                if (idTag != 0) {
                    if (!(isTagOnList(tag.tags, tagList))) {
                        insertTag(tag)
                        insertBookxTags(BookxTags(book.id, tag.id))
                    }
                }
            }
        }
    }

    fun insertListPublisher(publishers: ArrayList<String>): Int {
        var publishersList = allPublishers.value
        lateinit var publisher: Publisher
        if (publishersList != null) {
            var idEditorial: Int
            if (publishersList.isNotEmpty()) idEditorial =
                publishersList.get(publishersList.indexOf(publishersList.get(publishersList.size - 1))).id
            else idEditorial = 0
            for (i: Int in 1..publishers.size) {
                publisher = Publisher(idEditorial + i, publishers[i - 1])
                if (idEditorial != 0) {
                    if (!(isPublisherOnList(publisher.name, publishersList))) {
                        insertPublisher(publisher)
                        return publisher.id
                    } else {
                        return getPublisherIndex(publisher.name, publishersList)
                    }
                } else {
                    insertPublisher(publisher)
                    return 1
                }
            }
        }
        return -1;
    }

    private fun isAuthorOnList(name: String, authorList: List<Author>): Boolean {
        for (author: Author in authorList) {
            if (name == author.name_author) return true
        }
        return false
    }

    private fun isTagOnList(tag: String, tagList: List<Tags>): Boolean {
        for (tags: Tags in tagList) {
            if (tag == tags.tags) return true
        }
        return false
    }

    private fun isPublisherOnList(name: String, publisherList: List<Publisher>): Boolean {
        for (publisher: Publisher in publisherList) {
            if (name == publisher.name) return true
        }
        return false
    }

    private fun getPublisherIndex(name: String, publisherList: List<Publisher>): Int {
        var i: Int = 1
        for (publisher: Publisher in publisherList) {
            if (name == publisher.name) return i
            i++
        }
        return -1
    }
}