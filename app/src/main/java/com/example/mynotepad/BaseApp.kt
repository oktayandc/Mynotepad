package com.example.mynotepad

import android.app.Application
import com.example.mynotepad.db.RepoDatabase
import com.example.mynotepad.utils.AppExecutors

class BaseApp : Application() {

    companion object {
        lateinit var database: RepoDatabase
        lateinit var mAppExecutors: AppExecutors
    }

    override fun onCreate() {
        super.onCreate()

        database = generateDatabase()
        mAppExecutors = AppExecutors()
    }

    fun generateDatabase(): RepoDatabase {
        return RepoDatabase.getDatabase(this)
    }
}