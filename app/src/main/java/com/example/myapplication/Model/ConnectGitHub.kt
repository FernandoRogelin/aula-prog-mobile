package com.example.myapplication.Model

import android.util.JsonReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ConnectGitHub {
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
                    return
                    var value = jsonReader.nextString()

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
}