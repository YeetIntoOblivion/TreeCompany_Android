package com.spanishinquisition.treecompany.rest

import com.spanishinquisition.treecompany.models.Project

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceHolder {

    //Get all PROJECTS in a platform
    @GET("api/projects/GetAllByPlatform")
    fun GetAllByPlatform(@Query("platformId") id: Int): Call<List<Project>>


}
