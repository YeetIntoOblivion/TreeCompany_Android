package com.spanishinquisition.treecompany.rest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

var base_url: String = "http://10.0.2.2:5000/"

class RetroFit {

    companion object {

        private var builder = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())

        private var retrofit: Retrofit = builder.build()

        private var loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private var httpClientBuilder = OkHttpClient.Builder()

        fun GetClientRf(): ApiService {

            if (!httpClientBuilder.interceptors().contains(loggingInterceptor)) {
                httpClientBuilder.addInterceptor(loggingInterceptor)
                builder = builder.client(httpClientBuilder.build())
                retrofit = builder.build()
            }
            return retrofit.create(ApiService::class.java)

        }
    }
}





