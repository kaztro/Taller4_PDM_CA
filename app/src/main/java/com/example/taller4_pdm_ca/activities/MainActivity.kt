package com.example.taller4_pdm_ca.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller4_pdm_ca.R
import com.example.taller4_pdm_ca.adapter.BookListAdapter
import com.example.taller4_pdm_ca.pojos.Book
import com.example.taller4_pdm_ca.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var bookViewModel: BookViewModel
    //private lateinit var authorViewModel: AuthorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_books)
        val adapter = BookListAdapter({item: Book -> clickitem(item)})
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        //authorViewModel = ViewModelProviders.of(this).get(AuthorViewModel::class.java)

        bookViewModel.allBooks.observe(this, Observer { books ->
            // Update the cached copy of the words in the adapter.
            books?.let { adapter.setWords(it) }
        })

        /*authorViewModel.allAuthors.observe(this, Observer { authors ->
            // Update the cached copy of the words in the adapter.
            authors?.let { adapter.setAuthors(it) }
        })*/


        fab.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, NewBookActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }
    fun clickitem(item: Book){
        Log.d("olv", item.title)
        val intent = Intent(this@MainActivity, NewBookActivity::class.java)
        startActivityForResult(intent, newWordActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                //val list = Array<Author>()
                val book = Book(0,"",it.getStringExtra(NewBookActivity.EXTRA_REPLY),"", "", "", false, 0)
                //val author = Author( it.getStringExtra(NewWordActivity.EXTRA_REPLY2),true)
                bookViewModel.insert(book)
                //authorViewModel.insert(author)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/
}
