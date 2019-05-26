package com.example.taller4_pdm_ca.fragments

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.taller4_pdm_ca.R
import com.example.taller4_pdm_ca.adapter.BookListAdapter
import com.example.taller4_pdm_ca.pojos.Book
import com.example.taller4_pdm_ca.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.view.*



class BookListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var bookViewModel: BookViewModel
    lateinit var bookadapter: BookListAdapter
    var listener: BookListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_book_list, container, false)
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        initRecyclerView(resources.configuration.orientation,view)

        return view
    }

    fun initRecyclerView(orientation: Int, container: View){
        val linearLayoutManager = LinearLayoutManager(this.context)
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            bookadapter = BookListAdapter({book: Book -> listener?.managePortraitClick(book)})
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            bookadapter = BookListAdapter({ book: Book -> listener?.manageLandscapeClick(book) })
        }
        container.rv_books.adapter = bookadapter

        bookViewModel.allBooks.observe(this, Observer { books ->
            books?.let{bookadapter.setWords(it)}
        })
        bookViewModel.allPublishers.observe(this, Observer { books ->
            books?.let{bookadapter.setPublishers(it)}
        })
        bookViewModel.allAuthors.observe(this, Observer { books ->
            books?.let{bookadapter.setAuthors(it)}
        })

        container.rv_books.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    private fun clickitem(item: Book){
        Log.d("alv", item.title)
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BookListener) {
            listener = context
        } else {
            //throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface BookListener {
        fun managePortraitClick(book: Book)
        fun manageLandscapeClick(book: Book)
    }

    companion object {
        fun newInstance() : BookListFragment{
            var newFragment = BookListFragment()
            return newFragment
        }
    }
}
