package com.example.taller4_pdm_ca.room

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taller4_pdm_ca.dao.AuthorDao
import com.example.taller4_pdm_ca.dao.BookDao
import com.example.taller4_pdm_ca.dao.PublisherDao
import com.example.taller4_pdm_ca.dao.TagsDao
import com.example.taller4_pdm_ca.pojos.Author
import com.example.taller4_pdm_ca.pojos.Book
import com.example.taller4_pdm_ca.pojos.Publisher
import com.example.taller4_pdm_ca.pojos.Tags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Author::class, Book::class, Publisher::class, Tags::class), version = 1)
public abstract class LibraryRoomDatabase : RoomDatabase() {
    abstract fun authorDao(): AuthorDao
    abstract fun bookDao(): BookDao
    abstract fun publisherDao(): PublisherDao
    abstract fun tagsDao(): TagsDao


    companion object {
        @Volatile
        private var INSTANCE: LibraryRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LibraryRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
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
                        populateDatabase(
                            database.authorDao(),
                            database.bookDao(),
                            database.publisherDao(),
                            database.tagsDao()
                        )
                    }
                }
            }


        }

        suspend fun populateDatabase(
            authorDao: AuthorDao,
            bookDao: BookDao,
            publisherDao: PublisherDao,
            tagsDao: TagsDao
        ) {
            var book = Book(0, "a", "a", "a", "a", "1", false)
            //Log.d("lista", book.title)
            bookDao.insert(book)
            //val all = bookDao.getAllBooks()
            //Log.d("lista", all[0].title)
        }

    }


}