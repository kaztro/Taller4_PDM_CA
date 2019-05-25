package com.example.taller4_pdm_ca.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taller4_pdm_ca.R
import com.example.taller4_pdm_ca.pojos.Author
import com.example.taller4_pdm_ca.pojos.Book
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class BookListAdapter (val clickListener : (Book) -> Unit
) : RecyclerView.Adapter<BookListAdapter.WordViewHolder>() {

    //private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Book>()
   //private var authors = emptyList<Author>()// Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Book, clickListener: (Book) -> Unit) = with(itemView){
            textView.text = item.title
            this.setOnClickListener{clickListener(item)}
        }
        //val wordItemView: TextView = itemView.findViewById(R.id.textView)
        //val authItemView: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) = holder.bind(words[position], clickListener) /*{
        //Log.d("algo", words.size.toString() + authors.size.toString())
        val current = words[position]
        //val currenta = authors[position]
        holder.wordItemView.text = current.title
        //holder.authItemView.text = currenta.name_author
    }*/

    internal fun setWords(words: List<Book>) {
        this.words = words
        notifyDataSetChanged()
    }

    /*internal fun setAuthors(authors: List<Author>){
        this.authors = authors
        notifyDataSetChanged()
    }*/
    override fun getItemCount() = words.size
}