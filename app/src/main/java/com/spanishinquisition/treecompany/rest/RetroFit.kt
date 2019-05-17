package com.spanishinquisition.treecompany.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun GetRetroFit(): JsonPlaceHolder {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://10.0.2.2:5001/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(JsonPlaceHolder::class.java)

    return service

}