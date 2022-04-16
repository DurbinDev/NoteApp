package com.example.noteapp.util

import androidx.room.TypeConverter
import java.util.*

class DateConvertor {
    @TypeConverter
    fun timeStampFromDate(date: Date): Long{
        return date.time
    }
    @TypeConverter
    fun dateFromTimeStamp(timestamp: Long): Date? {
        return Date(timestamp)
    }
}