package com.example.taller4_pdm_ca.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taller4_pdm_ca.R
import com.example.taller4_pdm_ca.pojos.Author
import com.example.taller4_pdm_ca.pojos.Book
import com.example.taller4_pdm_ca.pojos.Publisher
import kotlinx.android.synthetic.main.activity_viewer.view.*

class MainContentFragment : Fragment() {
    private var book = Book()
    private var authBook = Author()
    private var publiBook = Publisher()

    companion object {
        fun newInstance(dataset: Book): MainContentFragment {
            return MainContentFragment().apply { book = dataset }
        }
    }

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
}