package com.example.taller4_pdm_ca

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import com.example.taller4_pdm_ca.activities.NewBookActivity
import com.example.taller4_pdm_ca.fragments.BookListFragment
import com.example.taller4_pdm_ca.pojos.Book
import com.example.taller4_pdm_ca.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookListFragment.BookListener {

    private val newWordActivityRequestCode = 1

    var cont : Int = 0

    private lateinit var bookViewModel: BookViewModel
    private lateinit var listFragment: BookListFragment
    //private lateinit var authorViewModel: AuthorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener { view ->
            cont++
            startActivity(Intent(this, NewBookActivity::class.java).putExtra("cont", cont))
            //val intent = Intent(this@MainActivity, NewBookActivity::class.java)
            //startActivityForResult(intent, newWordActivityRequestCode)
        }

        initFragment()
    }


    fun initFragment(){
        listFragment = BookListFragment.newInstance()

        val resource = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

            R.id.primarymain

        }
        else {
            R.id.primarymain
            Log.d("olv", "No hay fragmento")
        }
        changeFragment(resource,listFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit()}

    override fun managePortraitClick(book: Book) {
        Log.d("olv", "Algo")
        Toast.makeText(this, book.title, Toast.LENGTH_SHORT).show()
    }

    override fun manageLandscapeClick(book: Book) {
        Log.d("llv", book.title)
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
    }*/

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

