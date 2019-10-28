package com.example.mynotepad.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotepad.DBase

@Database(entities = [DBase::class], version = 1)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun notedao(): NoteDao

    companion object {

        @Volatile
        private var INSTANCE: RepoDatabase? = null

        fun getDatabase(context: Context): RepoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RepoDatabase::class.java,
                    "notes"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}