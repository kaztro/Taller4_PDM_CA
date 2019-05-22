package com.example.taller4_pdm_ca.room

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taller4_pdm_ca.dao.BookDao
import com.example.taller4_pdm_ca.pojos.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Book::class), version = 1)
public abstract class LibraryRoomDatabase : RoomDatabase(){
    abstract fun bookDao() : BookDao


    companion object{
        @Volatile
        private var INSTANCE : LibraryRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LibraryRoomDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibraryRoomDatabase::class.java,
                    "Library_Database"
                ).addCallback(LibraryDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }

        private class LibraryDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.bookDao())
                    }
                }
            }


        }
        suspend fun populateDatabase(bookDao: BookDao){
            var book = Book("a","a","a","a","a")
            Log.d("lista", book.title)
            bookDao.insert(book)
            //val all = bookDao.getAllBooks()
            //Log.d("lista", all[0].title)
        }

    }


}