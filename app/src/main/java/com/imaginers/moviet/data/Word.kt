package com.imaginers.moviet.data

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
    @Nullable @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "word") val word: String)