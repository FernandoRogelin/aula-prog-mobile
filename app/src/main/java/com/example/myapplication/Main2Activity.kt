package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*
import android.os.AsyncTask
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import android.util.JsonReader




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
        DadosAssincronos()

    }

    fun buscaDadosAssincronamente() {
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

    fun ConnectGitHub() {
        val githubEndpoint = URL("https://api.github.com/")
        val myConnection = githubEndpoint.openConnection() as HttpsURLConnection

        myConnection.setRequestProperty("Accept",
            "application/vnd.github.v3+json");
        myConnection.setRequestProperty("Contact-Me",
            "hathibelagal@example.com");

        if (myConnection.getResponseCode() == 200) {
            val responseBody = myConnection.inputStream

            val responseBodyReader = InputStreamReader(responseBody, "UTF-8")

            val jsonReader = JsonReader(responseBodyReader)

            jsonReader.beginObject() // Start processing the JSON object
            while (jsonReader.hasNext()) { // Loop through all keys
                val key = jsonReader.nextName() // Fetch the next key
                if (key == "organization_url") { // Check if desired key
                    // Fetch the value as a String
                    val value = jsonReader.nextString()

                    dataGitHub.append(value)
                    dataGitHub.append("\n")

                    // Do something with the value
                    // ...

                    break // Break out of the loop
                } else {
                    jsonReader.skipValue() // Skip values of other keys
                }
            }
            jsonReader.close();
            myConnection.disconnect();
        } else {
            // Error handling code goes here
        }
    }

    inner class DadosAssincronos : AsyncTask<Void, Void, Void>() {

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg params: Void): Void? {
            buscaDadosAssincronamente()
            ConnectGitHub()
            return null
        }
    }
}
