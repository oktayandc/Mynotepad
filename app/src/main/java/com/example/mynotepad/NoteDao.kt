package com.example.mynotepad

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {
    @Query("SELECT * FROM notlar")
    fun getAllNotlar(): LiveData<List<DBase>>

    @Query("SELECT * FROM notlar WHERE baslik = :mBaslik AND metin = :mMetin")
    fun getNot(mBaslik: String, mMetin: String): DBase

    @Insert
    fun insert(vararg note: DBase)

    @Update
    fun update(vararg note: DBase)

    @Delete
    fun delete(vararg note: DBase)
}