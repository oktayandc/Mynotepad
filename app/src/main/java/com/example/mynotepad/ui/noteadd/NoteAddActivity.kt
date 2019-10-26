package com.example.mynotepad.ui.noteadd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.mynotepad.*
import com.example.mynotepad.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_add_note.*

class NoteAddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val intent = intent
        val dbSonucu = intent.getParcelableExtra<DBase>("test")
        val notAction = intent.getStringExtra("notAction")

        if (notAction == NotActionType.NOT_GUNCELLE.name) {
            etBaslik.setText(dbSonucu.baslik)
            etMetin.setText(dbSonucu.metin)
            Toast.makeText(this, dbSonucu.metin, Toast.LENGTH_LONG).show()
        }

        val db: RepoDatabase = Room.databaseBuilder(this, RepoDatabase::class.java, "notes")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        btnKaydet.setOnClickListener {
            if (notAction == NotActionType.NOT_EKLE.name) {
                val note = DBase(0, etBaslik.text.toString(), etMetin.text.toString())
                db.notedao().insert(note)
                finish()
            } else if (notAction.equals(NotActionType.NOT_GUNCELLE.name)) {
                val note = DBase(dbSonucu._id, etBaslik.text.toString(), etMetin.text.toString())
                db.notedao().update(note)
                finish()
            }
        }

        btnSil.setOnClickListener {
            if (dbSonucu != null) {
                val note = DBase(dbSonucu._id, etBaslik.text.toString(), etMetin.text.toString())
                db.notedao().delete(note)
            } else {
                val note = DBase(0, etBaslik.text.toString(), etMetin.text.toString())
                db.notedao().delete(note)
            }
            finish()
        }
        btnVazgec.setOnClickListener {
            val intentgeri = Intent(this, MainActivity::class.java)
            startActivity(intentgeri)
            finish()
        }
    }

}