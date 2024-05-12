package com.example.notepad_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad_app.Note



class NoteAdapter(private var notes: List<Note>, context: Context): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val db: NoteDatabaseHelper = NoteDatabaseHelper(context)

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteText: TextView = itemView.findViewById<TextView>(R.id.note_title)
        val desc: TextView = itemView.findViewById<TextView>(R.id.note_content)
        val date: TextView = itemView.findViewById<TextView>(R.id.note_date)
        val time: TextView = itemView.findViewById<TextView>(R.id.note_time)
        val updatebtn: ImageView = itemView.findViewById<ImageView>(R.id.edit_note)
        val deletebtn: ImageView = itemView.findViewById<ImageView>(R.id.delete_note)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_items, parent, false)
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

        holder.updatebtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, Update_NoteActivity::class.java).apply {
                putExtra("note_id", currentNote.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deletebtn.setOnClickListener {
            db.deleteNoteById(currentNote.id)
            refresh(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Note Deleted", Toast.LENGTH_SHORT).show()
        }
    }


        fun refresh(note: List<Note>) {
            notes = note
            notifyDataSetChanged()
        }


    }

