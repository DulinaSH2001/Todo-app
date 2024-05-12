package com.example.notepad_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notepad_app.databinding.ActivityAddNoteBinding

class AddTodo : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var bd: TODODatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bd = TODODatabaseHelper(this)
        binding.saveBtn.setOnClickListener {
            val title = binding.taskInput.text.toString()
            val content = binding.taskDiscription.text.toString()
            val date = binding.taskDate.dayOfMonth.toString() + "/" + binding.taskDate.month.toString() + "/" + binding.taskDate.year.toString()
            val time = binding.taskTime.hour.toString() + ":" + binding.taskTime.minute.toString()
            val todo = Note (0, title, content, date, time)

            finish()
            val success = bd.insertTodo(todo)
            if (success) {
                Toast.makeText(this, "Todo Added", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to add Todo", Toast.LENGTH_SHORT).show()
            }
        }
        binding.backButton.setOnClickListener {
            navigateToMain()

        }




    }
   private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity
    }

}