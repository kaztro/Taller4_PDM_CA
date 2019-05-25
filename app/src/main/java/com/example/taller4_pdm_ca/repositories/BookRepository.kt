package com.example.taller4_pdm_ca.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4_pdm_ca.dao.*
import com.example.taller4_pdm_ca.pojos.*


class BookRepository(
    private val bookDao: BookDao,
    private val authorDao: AuthorDao,
    private val tagsDao: TagsDao,
    private val publisherDao: PublisherDao,
    private val authorxBookDao: AuthorxBookDao,
    private val bookxTagsDao: BookxTagsDao
) {

    val allBooks: LiveData<List<Book>> = bookDao.getAllBooks()
    val favoritesBooks: LiveData<List<Book>> = bookDao.getFavoritesBooks()
    val allAuthors: LiveData<List<Author>> = authorDao.getAllAuthors()
    val allPublishers: LiveData<List<Publisher>> = publisherDao.getAllPublishers()
    val allTags: LiveData<List<Tags>> = tagsDao.getAllTags()
    val allAuthorxBooks: LiveData<List<AuthorxBook>> = authorxBookDao.getAllAuthorxBooks()
    val allBookxTags: LiveData<List<BookxTags>> = bookxTagsDao.getAllBookTags()

    @WorkerThread
    suspend fun insertBook(book: Book) {
        bookDao.insert(book)
    }

    @WorkerThread
    suspend fun addFavorite(id: Int) {
        bookDao.addFavorite(id)
    }

    @WorkerThread
    suspend fun removeFavorite(id: Int) {
        bookDao.removeFavorite(id)
    }

    @WorkerThread
    suspend fun insertAuthor(author: Author) {
        authorDao.insert(author)
    }

    fun getAuthorsForBook(bookId: Int): LiveData<List<Author>> {
        return authorxBookDao.getAuthorsForBook(bookId)
    }

    fun getBooksForAuthor(authorId: Int): LiveData<List<Book>> {
        return authorxBookDao.getBooksForAuthor(authorId)
    }

    @WorkerThread
    suspend fun insertAuthorxBook(authorxBook: AuthorxBook) {
        authorxBookDao.insert(authorxBook)
    }

    @WorkerThread
    suspend fun insertTags(tags: Tags) {
        tagsDao.insert(tags)
    }

    @WorkerThread
    suspend fun insertPublisher(publisher: Publisher) {
        publisherDao.insert(publisher)
    }

    fun getTagsForBook(bookId: Int): LiveData<List<Tags>> {
        return bookxTagsDao.getTagsForBook(bookId)
    }

    fun getBooksForTag(tagId: Int): LiveData<List<Book>> {
        return bookxTagsDao.getBooksForTag(tagId)
    }

    @WorkerThread
    suspend fun insertBookxTags(bookxTags: BookxTags) {
        bookxTagsDao.insert(bookxTags)
    }
}