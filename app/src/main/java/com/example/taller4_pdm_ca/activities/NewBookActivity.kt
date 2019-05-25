package com.example.taller4_pdm_ca.activities


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.taller4_pdm_ca.R

class NewBookActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    //private lateinit var editAuthorView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)
        editWordView = findViewById(R.id.edit_word)
        //editAuthorView = findViewById(R.id.edit_auth)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text) /*and TextUtils.isEmpty(editAuthorView.text)*/) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }
            //else if (TextUtils.isEmpty(editAuthorView.text)){setResult(Activity.RESULT_CANCELED, replyIntent)}

            else {
                val word = editWordView.text.toString()
                //val author = editAuthorView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                //replyIntent.putExtra(EXTRA_REPLY2, author)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        const val EXTRA_REPLY2 = "com.example.android.wordlistsql.REPLY"
    }
}