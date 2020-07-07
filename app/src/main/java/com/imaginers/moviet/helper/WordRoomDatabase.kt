package com.imaginers.moviet.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.imaginers.moviet.data.Word
import com.imaginers.moviet.data.WordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Word::class], version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

//        fun getDatabase(
//            context: Context,
//            scope: CoroutineScope
//        ): WordRoomDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    WordRoomDatabase::class.java,
//                    "word_database.db"
//                )
//                    .addCallback(WordDatabaseCallback(scope))
//                    .build()
//                INSTANCE = instance
//                return instance
//            }
//        }

        /**
         *  code from shafi
         */

        fun getInstance(context: Context): WordRoomDatabase = INSTANCE
            ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    ).also {
                        INSTANCE = it
                    }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WordRoomDatabase::class.java,
                "word_database.db"
            ).build()

    }


//    private class WordDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    populateDatabase(database.wordDao())
//                }
//            }
//        }
//
//        suspend fun populateDatabase(wordDao: WordDao) {
//            // Delete all content here.
//            wordDao.deleteAll()
//
//            // Add sample words.
//            var word = Word(null, "Hello")
//            wordDao.insert(word)
//            word = Word(null, "World!")
//            wordDao.insert(word)
//
//            // TODO: Add your own words!
//        }
//
//        companion object {
//            @Volatile
//            private var INSTANCE: WordRoomDatabase? = null
//
//            fun getDatabase(
//                context: Context,
//                scope: CoroutineScope
//            ): WordRoomDatabase {
//                // if the INSTANCE is not null, then return it,
//                // if it is, then create the database
//                return INSTANCE ?: synchronized(this) {
//                    val instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        WordRoomDatabase::class.java,
//                        "word_database"
//                    )
//                        .addCallback(WordDatabaseCallback(scope))
//                        .build()
//                    INSTANCE = instance
//                    // return instance
//                    instance
//                }
//            }
//        }
//    }
}