package com.example.mynotepad

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_add_note.*

class CreateNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db: RepoDatabase =
            Room.databaseBuilder(applicationContext, RepoDatabase::class.java, "notes")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

//        btnKaydet.setOnClickListener {
//            val note: DBase = DBase(1, txtBaslik.text.toString(), txtMetin.text.toString())
//            db.notedao().insert(DBase())
//            startActivity(Intent(this@CreateNote, MainActivity::class.java))
//            finish()
//        }
    }
}