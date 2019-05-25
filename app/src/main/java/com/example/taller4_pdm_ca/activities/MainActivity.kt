package com.example.taller4_pdm_ca.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taller4_pdm_ca.R
import com.example.taller4_pdm_ca.adapters.BookAdapter
import com.example.taller4_pdm_ca.pojos.Book
import com.example.taller4_pdm_ca.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var bookViewModel: BookViewModel
    private var flag = true
    private var switch = true
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bookAdapter = BookAdapter(this, { book: Book -> favoriteBook(book) }, { book: Book -> triggerActivity(book) })


        //var bookAdapter = BooksAdapter(this) { book:Book->bookOnClicked(book)}
        rv_books.adapter = bookAdapter
        rv_books.layoutManager = LinearLayoutManager(this)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)



        bookViewModel.allBooks.observe(this, Observer { books ->
            bookAdapter.setBooks(books)
        })
        initClicksListeners()
    }

    private fun initClicksListeners(){
        btn_favorites.setOnClickListener(){
            bookViewModel.favoriteBooks.observe(this, Observer { favBooks -> //This favorites books deberia estar en ViewModel
                bookAdapter.setBooks(favoriteBooks)
            })
        }
    }

    private fun triggerActivity(book: Book) {
        val bundleBook = Bundle()
            bundleBook.putParcelable("BOOK", book)
        startActivity(Intent(this, ViewerActivity::class.java).putExtras(bundleBook))
    }

    private fun bookFavorito(book: Book) {
        bookViewModel.favoriteBooks.observe(this, Observer { favorites ->
            bookAdapter.setBooks(favorites)
        })
        bookViewModel.chekinoutF(book)
    }
}
