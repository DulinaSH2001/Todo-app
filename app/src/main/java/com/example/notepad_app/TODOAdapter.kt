package com.example.notepad_app

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class TODOAdapter(private var todos: List<Note>, context: Context): RecyclerView.Adapter<TODOAdapter.TodoViewHolder>() {

    private val db: TODODatabaseHelper = TODODatabaseHelper(context)

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todoText: TextView = itemView.findViewById<TextView>(R.id.note_title)
        val desc: TextView = itemView.findViewById<TextView>(R.id.note_content)
        val date: TextView = itemView.findViewById<TextView>(R.id.note_date)
        val time: TextView = itemView.findViewById<TextView>(R.id.note_time)
        val updatebtn: ImageView = itemView.findViewById<ImageView>(R.id.edit_note)
        val deletebtn: ImageView = itemView.findViewById<ImageView>(R.id.delete_note)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_items, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {

        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.todoText.text = currentTodo.title
        holder.desc.text = currentTodo.content
        holder.date.text = currentTodo.date
        holder.time.text = currentTodo.time

        holder.updatebtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, Update_Todo_Activity::class.java).apply {
                putExtra("todo_id", currentTodo.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deletebtn.setOnClickListener {
            db.deleteTodoById(currentTodo.id)
            refresh(db.getAllTodo())
            Toast.makeText(holder.itemView.context, "Todo Deleted", Toast.LENGTH_SHORT).show()
        }
    }


        fun refresh(todo: List<Note>) {
            todos = todo
            notifyDataSetChanged()
        }


    }

