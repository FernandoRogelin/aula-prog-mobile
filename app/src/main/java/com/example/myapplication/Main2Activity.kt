package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        button4.setOnClickListener {
            val dbHandler = MindOrksDBOpenHelper(this, null)
            val user = Name(editText.text.toString())
            dbHandler.addName(user)
            Toast.makeText(this, editText.text.toString() + "Added to database", Toast.LENGTH_LONG).show()
        }

        button3.setOnClickListener {
            tvDisplayName.text = ""
            val dbHandler = MindOrksDBOpenHelper(this, null)
            val cursor = dbHandler.getAllName()
            cursor!!.moveToFirst()
            tvDisplayName.append((cursor.getString(cursor.getColumnIndex(MindOrksDBOpenHelper.COLUMN_NAME))))
            while (cursor.moveToNext()) {
                tvDisplayName.append((cursor.getString(cursor.getColumnIndex(MindOrksDBOpenHelper.COLUMN_NAME))))
                tvDisplayName.append("\n")
            }
            cursor.close()
        }
    }

    fun clickClickMeButton(view: View) {
        val intent = Intent( this, Main3Activity::class.java)
        startActivity(intent)
    }

    class Name {
        var id: Int = 0
        var userName: String? = null
        constructor(id: Int, userName: String) {
            this.id = id
            this.userName = userName
        }
        constructor(userName: String) {
            this.userName = userName
        }
    }
}
