package com.spanishinquisition.treecompany.rest

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import com.spanishinquisition.treecompany.models.Project
import java.io.IOException
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.InputStreamReader
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

const val BASE_URL = "https://10.0.2.2:5001"

class RestClient(private val context: Context) {
    private fun getUnsafeOkHttpClient(): OkHttpClient {
        try {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

                @SuppressLint("TrustAllX509TrustManager")
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }
            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    private fun connect(urlString: String): Response {
        val connMgr = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            try {
                val request = Request.Builder()
                    .url(urlString)
                    .build()

                val client = getUnsafeOkHttpClient()
                return client.newCall(request).execute()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        throw IOException()
    }

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

                val client = getUnsafeOkHttpClient()
                return client.newCall(request).execute().isSuccessful
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return false
    }

    fun getProjects(platformId: Int): Observable<Array<Project>> {
        return Observable.create { emitter ->
            try {
                val response = connect("$BASE_URL/api/Project/GetAllByPlatform?platformId=$platformId")
                val gson = GsonBuilder().create()
                val projects =
                    gson.fromJson(InputStreamReader(response.body()!!.byteStream()), Array<Project>::class.java)
                emitter.onNext(projects)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}
