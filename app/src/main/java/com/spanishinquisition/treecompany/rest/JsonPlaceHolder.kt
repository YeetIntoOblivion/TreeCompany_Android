package com.spanishinquisition.treecompany.rest

import com.spanishinquisition.treecompany.models.projects.Ideation
import com.spanishinquisition.treecompany.models.projects.Module
import com.spanishinquisition.treecompany.models.projects.Project
import com.spanishinquisition.treecompany.models.projects.Questionnaire

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface JsonPlaceHolder {

    //PROJECTCONTROLLER
    //Get all PROJECTS in a platform
    @GET("api/project/GetAllByPlatform")
    fun GetAllByPlatform(@Query("platformId") id: Int): Call<List<Project>>

    //GET PROJECT BY ID
    @GET("api/project/GetById")
    fun GetById(@Query("projectId") id: Int): Call<Project>

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




}
