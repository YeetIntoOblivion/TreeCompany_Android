package com.spanishinquisition.treecompany.rest

import com.google.gson.GsonBuilder
import com.spanishinquisition.treecompany.models.Idea
import com.spanishinquisition.treecompany.models.User
import com.spanishinquisition.treecompany.models.projects.Ideation
import com.spanishinquisition.treecompany.models.projects.Module
import com.spanishinquisition.treecompany.models.projects.Project
import com.spanishinquisition.treecompany.models.projects.Questionnaire
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

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

    //USERCONTROLLER
    @FormUrlEncoded
    @POST("api/User/Login")
    fun userLogin(
        @Field("Email")email:String,
        @Field("Password")passwd:String

    ):Call<User>

    //PROJECTCONTROLLER
    //Get all PROJECTS in a platform
    @GET("api/project/GetAllByPlatform")
    fun GetAllByPlatform(@Query("platformId") plaformId: Int): Call<List<Project>>

    //GET PROJECT BY ID
    @GET("api/project/GetById")
    fun GetById(@Query("projectId") projectId: Int): Call<Project>

    //GET SORTED PROJECTS
    @GET("api/project/SortedBy")
    fun SortedBy(@Query("quota") quota: Int, @Query("platformId") platformId: Int): Call<List<Project>>

    //TODO(put)


    //MODULECONTROLLER
    //GET list all the modules of one project
    @GET("api/module/GetModules")
    fun GetModules(@Query("projectId") id: Int): Call<List<Module>>


    @GET("api/module/GetQuestionnaire")
    fun GetQuestionnaire(@Query("projectId") projectId: Int, @Query("phaseId") phaseId: Int): Call<Questionnaire>

    @GET("api/module/GetIdeation")
    fun GetIdeation(@Query("projectId") projectId: Int, @Query("phaseId") phaseId: Int): Call<Ideation>

    @GET("api/module/GetModuleForPhase")
    fun GetModuleForPhase(@Query("phaseId") phaseId: Int): Call<Module>

    // TODO() POST AND PUT


    @GET("api/module/GetIdeas")
    fun GetIdeas(@Query("questionId") questionId: Int): Call<List<Idea>>
}