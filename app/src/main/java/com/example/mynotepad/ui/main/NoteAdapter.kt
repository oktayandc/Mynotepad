package com.example.mynotepad

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_not_layout.view.*

class NoteAdapter(
    private val notes: List<DBase>,
    private val dbase: (dBase: DBase) -> Unit
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.item_not_layout,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ui(notes[position], dbase)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun ui(dBase: DBase, db: (dBase: DBase) -> Unit) {
            view.etBaslik.text = dBase.baslik
            view.etBaslik.setOnClickListener {
                db.invoke(dBase)
//                if (dBase.baslik == "test") {
//                    view.txtBaslik.text = "oktay"
//                }
            }
        }
    }
}