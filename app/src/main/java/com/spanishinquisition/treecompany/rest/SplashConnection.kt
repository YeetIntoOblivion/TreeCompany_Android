package com.spanishinquisition.treecompany.rest

import android.content.Context
import android.net.ConnectivityManager
import java.io.IOException
import okhttp3.Request

const val BASE_URL = "https://10.0.2.2:5001"

class SplashConnection(private val context: Context) {
    fun isConnectedToServer(): Boolean {
        val connMgr = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            try {
                val request = Request.Builder()
                    .url(BASE_URL)
                    .build()

                val client = UnsafeOkHttpClient.getUnsafeOkHttpClient()
                return client.newCall(request).execute().isSuccessful
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return false
    }
}