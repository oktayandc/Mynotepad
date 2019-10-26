package com.example.mynotepad

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DBase::class],version = 1)
abstract class RepoDatabase:RoomDatabase() {
    abstract fun notedao():NoteDao
}