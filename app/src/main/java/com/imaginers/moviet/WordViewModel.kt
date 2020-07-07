package com.imaginers.moviet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imaginers.moviet.data.DataState
import com.imaginers.moviet.data.Word
import com.imaginers.moviet.helper.WordRoomDatabase
import com.imaginers.moviet.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: WordRepository

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
//    val allWords: LiveData<List<Word>>

//    init {
//        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
//        repository = WordRepository(wordsDao)
//        allWords = repository.allWords
//    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
//    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
//        repository.insert(word)
//    }

    /**
     * modified by shafi
     */

    val state: MutableLiveData<DataState>

    init {
        repository = WordRepository(application)
        state = MutableLiveData()
        getAllWords()
    }

    fun insertWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWord(word)
        }
    }

    fun getAllWords() {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(DataState.success(repository.getAllWords()))
        }
    }

}
