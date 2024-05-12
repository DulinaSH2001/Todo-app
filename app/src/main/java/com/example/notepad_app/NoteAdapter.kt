package com.example.notepad_app

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad_app.Note



class NoteAdapter(private var notes: List<Note>, context: Context): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val noteText: TextView = itemView.findViewById<TextView>(R.id.note_title)
        val desc: TextView = itemView.findViewById<TextView>(R.id.note_content)
        val date:TextView= itemView.findViewById<TextView>(R.id.note_date)
        val time:TextView = itemView.findViewById<TextView>(R.id.note_time)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from( parent.context).inflate(R.layout.note_items,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {

        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.noteText.text = currentNote.title
        holder.desc.text = currentNote.content
        holder.date.text = currentNote.date
        holder.time.text = currentNote.time
    }



    fun refresh(note:List<Note>){
        notes = note
        notifyDataSetChanged()
    }


}