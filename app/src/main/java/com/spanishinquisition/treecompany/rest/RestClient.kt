package com.spanishinquisition.treecompany.rest

import android.content.Context
import android.database.Observable
import android.net.ConnectivityManager
import android.util.Log
import com.google.gson.GsonBuilder
import com.spanishinquisition.treecompany.models.Project
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
/*
import io.reactivex.Observable
*/
import java.io.InputStreamReader

const val INDEX_URL = "https://10.0.2.2:5001"
const val BASE_URL = "https://10.0.2.2:5001"

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
            val myUrl = URL(INDEX_URL)
            val connection = myUrl.openConnection()
            connection.connectTimeout = 5000
            connection.connect()
            return true
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
            //TODO switch this back to false after disabling certificate check
            return true
        }
    }

    /*fun getProjects(platformId: Int): Observable<Array<Project>> {
        return Observable.create { emitter ->
            try {
                val connection = connect("$BASE_URL/api/Project/GetAllByPlatform?platformId=$platformId")
                val gson = GsonBuilder().create()
                val projects = gson.fromJson(InputStreamReader(connection.inputStream), Array<Project>::class.java)
                emitter.onNext(projects)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }*/
}
