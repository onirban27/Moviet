package com.imaginers.moviet.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imaginers.moviet.data.Word
import com.imaginers.moviet.data.WordDao
import com.imaginers.moviet.helper.WordRoomDatabase

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO

/**
 *  modified by shafi
 */
class WordRepository(context: Context) {

    private val wordDao: WordDao

    init {
        val wordDatabase = WordRoomDatabase.getInstance(context)
        wordDao = wordDatabase.wordDao()
    }

    //insert data
    suspend fun insertWord(word: Word) {
        wordDao.insert(word)
    }

    //get all data
    suspend fun getAllWords(): List<Word> {
        return wordDao.getAlphabetizedWords()
    }
}