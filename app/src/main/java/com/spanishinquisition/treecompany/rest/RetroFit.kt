package com.spanishinquisition.treecompany.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun GetRetroFit(): JsonPlaceHolder {

    val builder = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = builder.create(JsonPlaceHolder::class.java)

    return service

}