package com.example.taller4_pdm_ca.viewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taller4_pdm_ca.pojos.Book
import com.example.taller4_pdm_ca.repositories.BookRepository
import com.example.taller4_pdm_ca.room.LibraryRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BookRepository
    val allBooks: LiveData<List<Book>>

    init {
        val booksDao = LibraryRoomDatabase.getDatabase(application,viewModelScope).bookDao()
        repository = BookRepository(booksDao)
        allBooks = repository.allBooks
    }

    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(book)
    }
}