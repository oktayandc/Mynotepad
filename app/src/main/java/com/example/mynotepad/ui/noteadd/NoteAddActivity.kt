package com.example.mynotepad.ui.noteadd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.example.mynotepad.*
import com.example.mynotepad.db.RepoDatabase
import com.example.mynotepad.ui.main.MainActivity
import com.example.mynotepad.ui.main.MainViewModel
import kotlinx.android.synthetic.main.activity_add_note.*

class NoteAddActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private var note: DBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val intent = intent
        val dbSonucu = intent.getParcelableExtra<DBase>("test")
        val notAction = intent.getStringExtra("notAction")

        if (notAction == NotActionType.NOT_GUNCELLE.name) {
            etBaslik.setText(dbSonucu.baslik)
            etMetin.setText(dbSonucu.metin)
            Toast.makeText(this, dbSonucu.metin, Toast.LENGTH_LONG).show()
        }

        btnKaydet.setOnClickListener {
            if (notAction == NotActionType.NOT_EKLE.name) {
                note = DBase(0, etBaslik.text.toString(), etMetin.text.toString())
                finish()
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right)
                mainViewModel.insertNote(note!!)
            } else if (notAction.equals(NotActionType.NOT_GUNCELLE.name)) {
                note = DBase(dbSonucu._id, etBaslik.text.toString(), etMetin.text.toString())
                finish()
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right)
                mainViewModel.updateNote(note!!)
            }
        }

        btnSil.setOnClickListener {
            if (dbSonucu != null) {
                note = DBase(dbSonucu._id, etBaslik.text.toString(), etMetin.text.toString())
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right)
            } else {
                note = DBase(0, etBaslik.text.toString(), etMetin.text.toString())
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right)
            }
            note?.let {
                mainViewModel.deleteNote(it)
            }
            finish()
        }
        btnVazgec.setOnClickListener {
            val intentgeri = Intent(this, MainActivity::class.java)
            startActivity(intentgeri)
            finish()
            overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right)

        }
    }

}