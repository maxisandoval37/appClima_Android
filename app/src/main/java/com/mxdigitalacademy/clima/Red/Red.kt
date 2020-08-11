package com.mxdigitalacademy.clima.Red

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

class Red {

    companion object {

        fun verficarConexionInternet(contextActivity: AppCompatActivity): Boolean {
            val connectivityManager = contextActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo

            return (networkInfo != null) && networkInfo.isConnected
        }
    }
}