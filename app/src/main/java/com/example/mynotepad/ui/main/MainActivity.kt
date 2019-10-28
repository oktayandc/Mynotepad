package com.example.mynotepad.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mynotepad.*
import com.example.mynotepad.R
import com.example.mynotepad.db.RepoDatabase
import com.example.mynotepad.ui.noteadd.NoteAddActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var notes : List<DBase>? = null
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observeNots()

        fab.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
            val intent = Intent(this, NoteAddActivity::class.java)
            intent.putExtra("notAction", NotActionType.NOT_EKLE.name)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_in_right)

        }
    }

    private fun observeNots() {
        mainViewModel.getNotes().observe(this, Observer {
            it?.let { notlar ->
                if (notlar.size > 0) {
                    rvNotes.layoutManager= LinearLayoutManager(this)
                    rvNotes.adapter= NoteAdapter(notlar, {
                        val intent = Intent(this,NoteAddActivity::class.java)
                        intent.putExtra("notAction", NotActionType.NOT_GUNCELLE.name)
                        intent.putExtra("test", it)
                        startActivity(intent)
                    })
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
