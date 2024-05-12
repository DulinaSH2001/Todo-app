package com.example.notepad_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notepad_app.databinding.ActivityUpdateNoteBinding

class Update_NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private  lateinit var db:NoteDatabaseHelper
    private var noteId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

            db = NoteDatabaseHelper(this)
        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }
        val note = db.getNoteById(noteId)
        binding.editTaskInput.setText(note.title)
        binding.editTaskDiscription.setText(note.content)

        val dateParts = note.date.split("/")
        val day = dateParts[0].toInt()
        val month = dateParts[1].toInt() - 1 // Subtract 1 because months are zero-indexed
        val year = dateParts[2].toInt()


        binding.editTaskDate.updateDate(year, month, day)
        val timeParts = note.time.split(":")
        val hour = timeParts[0].toInt()
        val minute = timeParts[1].toInt()

// Set time in TimePicker (assuming editTaskTime is a TimePicker)
        binding.editTaskTime.hour = hour
        binding.editTaskTime.minute = minute

        binding.editBtn.setOnClickListener {
            val title = binding.editTaskInput.text.toString()
            val content = binding.editTaskDiscription.text.toString()
            val date = "${binding.editTaskDate.dayOfMonth}/${binding.editTaskDate.month + 1}/${binding.editTaskDate.year}"
            val time = "${binding.editTaskTime.hour}:${binding.editTaskTime.minute}"
            val note = Note(
                noteId,
                title,
                content,
                date,
                time
            )
            db.updateNote(note)
            finish()
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()

        }

    }
}