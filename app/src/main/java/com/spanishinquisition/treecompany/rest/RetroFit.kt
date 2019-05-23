package com.spanishinquisition.treecompany.rest

import com.google.gson.GsonBuilder
import com.spanishinquisition.treecompany.models.Idea
import com.spanishinquisition.treecompany.models.Platform
import com.spanishinquisition.treecompany.models.projects.Ideation
import com.spanishinquisition.treecompany.models.projects.Module
import com.spanishinquisition.treecompany.models.projects.Project
import com.spanishinquisition.treecompany.models.projects.Questionnaire
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

var base_url: String = "https://10.0.2.2:5001/"


fun getClient(): ApiService {
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    return retrofit.create(
        ApiService::class.java
    )
}

interface ApiService {
    //PLATFORMCONTROLLER
    //GET ALL PLATFORMS
    @GET("api/platform/GetPlatforms")
    fun getPlatforms() : Call<List<Platform>>

    //PROJECTCONTROLLER
    //GET PROJECT BY ID
    @GET("api/project/GetById")
    fun getById(@Query("projectId") projectId: Int): Call<Project>

    //GET SORTED PROJECTS BY PLATFORM ID
    @GET("api/project/SortedBy")
    fun sortedBy(@Query("quota") quota: Int, @Query("platformId") platformId: Int): Call<List<Project>>

    //TODO(put)


    //MODULECONTROLLER
    //GET list all the modules of one project
    @GET("api/module/GetModules")
    fun getModules(@Query("projectId") id: Int): Call<List<Module>>


    @GET("api/module/GetQuestionnaire")
    fun getQuestionnaire(@Query("projectId") projectId: Int, @Query("phaseId") phaseId: Int): Call<Questionnaire>

    @GET("api/module/GetIdeation")
    fun getIdeation(@Query("projectId") projectId: Int, @Query("phaseId") phaseId: Int): Call<Ideation>

    @GET("api/module/GetModuleForPhase")
    fun getModuleForPhase(@Query("phaseId") phaseId: Int): Call<Module>

    // TODO() POST AND PUT


    @GET("api/module/GetIdeas")
    fun getIdeas(@Query("id") id: Int): Call<List<Idea>>
}