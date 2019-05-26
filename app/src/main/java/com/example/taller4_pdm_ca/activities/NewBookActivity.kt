package com.example.taller4_pdm_ca.activities


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.taller4_pdm_ca.MainActivity
import com.example.taller4_pdm_ca.R
import com.example.taller4_pdm_ca.pojos.Author
import com.example.taller4_pdm_ca.pojos.Book
import com.example.taller4_pdm_ca.pojos.Publisher
import com.example.taller4_pdm_ca.viewmodel.BookViewModel

class NewBookActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var editeditView: EditText
    private lateinit var editisbnView: EditText
    private lateinit var editsynopsisView: EditText
    private lateinit var editpublisherView: EditText
    private lateinit var editauthView: EditText
    private lateinit var edittagsView: EditText



    private lateinit var bookViewModel: BookViewModel
    //private lateinit var editAuthorView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        val cont:Int = this.intent.extras.getInt("cont")
        editWordView = findViewById(R.id.edit_word)
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        editauthView = findViewById(R.id.edit_auth)
        editpublisherView = findViewById(R.id.edit_auth)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text) /*and TextUtils.isEmpty(editAuthorView.text)*/) {
                //setResult(Activity.RESULT_CANCELED, replyIntent)
                Toast.makeText(this, "no se puede ingresar", Toast.LENGTH_SHORT).show()
            }
            //else if (TextUtils.isEmpty(editAuthorView.text)){setResult(Activity.RESULT_CANCELED, replyIntent)}

            else {
                var sizeb  = 0
                bookViewModel.allBooks.observe(this, Observer { books ->
                    books?.let { sizeb = it.size }
                })

                Log.d("tamaño", sizeb.toString())

                val word = editWordView.text.toString()
                //val edit = editeditView.text.toString()
                //val isbn = editisbnView.text.toString()
                //val syno = editsynopsisView.text.toString()
                val publ = editpublisherView.text.toString()
                val auth = editauthView.text.toString()
                //val tag = edittagsView.text.toString()





                val book = Book(cont, "",word ,"", "", "", false, 0)
                val author = Author(cont, auth)
                val publisher = Publisher(cont, publ)
                bookViewModel.insertBook(book)
                bookViewModel.insertAuthor(author)
                bookViewModel.insertPublisher(publisher)
                //val author = editAuthorView.text.toString()
                //replyIntent.putExtra(EXTRA_REPLY, word)
                //replyIntent.putExtra(EXTRA_REPLY2, author)
                //setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        const val EXTRA_REPLY2 = "com.example.android.wordlistsql.REPLY"
    }
}