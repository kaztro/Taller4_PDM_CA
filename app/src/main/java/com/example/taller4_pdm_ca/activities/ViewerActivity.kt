package com.example.taller4_pdm_ca.activities
/*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.taller4_pdm_ca.R
import com.example.taller4_pdm_ca.pojos.Author
import com.example.taller4_pdm_ca.pojos.Book
import com.example.taller4_pdm_ca.pojos.Publisher
import com.example.taller4_pdm_ca.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_viewer.*
import kotlinx.android.synthetic.main.activity_viewer.view.*

class ViewerActivity : AppCompatActivity() {

    lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewer)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        val deserialicedBook: Book? = intent.extras.getParcelable("BOOK")
        if(deserialicedBook != null) {
            bookViewModel.allBooks.observe(this, Observer(init(bookViewModel)))
        }
    }

    fun init(book: Book) {
        Glide.with(this)
            .load(book.cover_url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(iv_book_cover_av)


    }
private var book = Book()
    private var authBook = Author()
    private var publiBook = Publisher()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.activity_viewer, container, false)

        bindData(view, book, authBook, publiBook)

        return view
    }

    private fun bindData(view: View, data: Book, dataAuthor: Author, dataPubli: Publisher){
        Glide.with(this)
            .load(data.cover_url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.iv_book_cover_av)

        view.tv_name.text = data.title
        view.tv_author_name.text = dataAuthor.name_author
        view.tv_isbn.text = data.isbn
        view.tv_edition.text = data.edition
        view.tv_publisher.text = dataPubli.name
        view.tv_synopsis.text = data.synopsis
    }
}*/