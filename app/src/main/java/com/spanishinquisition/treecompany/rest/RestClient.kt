package com.spanishinquisition.treecompany.rest

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

const val BASE_URL = "https://34.76.133.167/home/"

class RestClient(private val context: Context) {
    private fun connect(urlString: String): HttpURLConnection {
        val connMgr = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected()) {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.apply {
                connectTimeout = 10000
                readTimeout = 5000
                requestMethod = "GET"
                connect()
            }
            return connection
        }
        throw IOException("Unable to connect to network")
    }

    fun isConnectedToServer(): Boolean {
        try {
            val myUrl = URL(BASE_URL)
            val connection = myUrl.openConnection()
            connection.connectTimeout = 5000
            connection.connect()
            return true
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
            //TODO switch this back to false after disabling certificate check
            return false
        }
    }
}