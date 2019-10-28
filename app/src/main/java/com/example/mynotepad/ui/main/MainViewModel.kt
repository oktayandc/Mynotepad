package com.example.mynotepad.ui.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.mynotepad.BaseApp
import com.example.mynotepad.DBase
import com.example.mynotepad.db.RepoDatabase
import com.example.mynotepad.utils.AppExecutors

class MainViewModel: ViewModel() {

    private var db: RepoDatabase = BaseApp.database
    private var appExecutors: AppExecutors = BaseApp.mAppExecutors

    init {
        getNotes()
    }

    fun insertNote(dBase: DBase) {
        appExecutors.diskIO().execute {
            db.notedao().insert(dBase)
        }
    }
    fun updateNote(dBase: DBase){
        appExecutors.diskIO().execute{
            db.notedao().update(dBase)
        }
    }

    fun deleteNote(dBase: DBase) {
        appExecutors.diskIO().execute{
            db.notedao().delete(dBase)
        }

    }

    fun getNotes() : LiveData<List<DBase>> {
        return db.notedao().getAllNotlar()
    }

}