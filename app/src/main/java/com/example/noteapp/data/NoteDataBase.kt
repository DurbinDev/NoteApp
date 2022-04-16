package com.example.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteapp.model.Note
import com.example.noteapp.util.DateConvertor
import com.example.noteapp.util.UUIDConvertor


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConvertor::class, UUIDConvertor::class)
abstract class NoteDataBase: RoomDatabase() {
    abstract  fun noteDao(): NoteDataBaseDao
}