package com.example.notepad_app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDatabaseHelper (context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

companion object {
    private const val DATABASE_NAME = "note.db"
    private const val DATABASE_VERSION = 1
    private const val TABLE_NAME = "note"
    private const val ID = "id"
    private const val TITLE = "title"
    private const val CONTENT = "content"
    private const val DATE = "date"
    private const val TIME = "time"
    }

    override fun onCreate(db: SQLiteDatabase?) {
       val createTable = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $TITLE TEXT, $CONTENT TEXT, $DATE TEXT, $TIME TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }

    fun insertnote (note: note): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(TITLE, note.title)
            put(CONTENT, note.content)
            put(DATE, note.date)
            put(TIME, note.time)
        }

        val success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$success") != -1)
    }
}


