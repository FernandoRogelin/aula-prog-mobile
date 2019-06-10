package com.example.myapplication.ViewModel

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.example.myapplication.Model.ConnectGitHub

class Background {
    inner class DadosAssincronos : AsyncTask<String, String, String>() {

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg params: String?): String? {
            val value = ConnectGitHub().ConnectGitHub()
            return value.toString()
        }
    }
}