package com.spanishinquisition.treecompany.rest

import android.content.Context
import android.net.ConnectivityManager
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

const val BASE_URL = "http://10.0.2.2:5001"

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

    fun bootTestConnection() {
        val connection = connect(BASE_URL)
    }
}