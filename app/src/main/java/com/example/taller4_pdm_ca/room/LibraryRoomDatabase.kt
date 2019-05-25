package com.example.taller4_pdm_ca.room

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taller4_pdm_ca.dao.*
import com.example.taller4_pdm_ca.pojos.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Author::class, Book::class, Publisher::class, Tags::class), version = 2)
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
                ).fallbackToDestructiveMigration()
                .addCallback(LibraryDatabaseCallback(scope)).build()
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
            //publisherDao.deleteAll()
            //bookDao.deleteAll()


            val pub = Publisher(0,"Planeta")
            publisherDao.insert(pub)

            var book = Book(0, "a", "a", "a", "a", "1", false, 0)
            //Log.d("lista", book.title)
            bookDao.insert(book)



            //val all = bookDao.getAllBooks()
            //Log.d("lista", all[0].title)
        }

    }


}