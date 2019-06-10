package com.example.myapplication.ViewModel

import android.content.Context
import android.database.Cursor
import android.widget.EditText
import com.example.myapplication.Model.MindOrksDBOpenHelper
import com.example.myapplication.View.Main2Activity

class SQLiteVM {
    fun SQLiteAddUser(main2Activity: Context, editText: EditText) {
        val dbHandler = MindOrksDBOpenHelper(main2Activity, null)
        val user = Main2Activity.Name(editText.text.toString())
        dbHandler.addName(user)
    }

    fun SQLiteGetAllName(main2Activity: Context): Cursor? {
        val dbHandler = MindOrksDBOpenHelper(main2Activity, null)
        val cursor = dbHandler.getAllName()
        return cursor
    }
}