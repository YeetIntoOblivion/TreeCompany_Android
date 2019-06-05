package com.spanishinquisition.treecompany.rest

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import com.spanishinquisition.treecompany.models.Idea
import com.spanishinquisition.treecompany.models.User
import com.spanishinquisition.treecompany.models.Platform
import com.spanishinquisition.treecompany.models.projects.*
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException

/*
 *  @author David Matei & Edwin Kai-Yin Tam
 */

//var BASE_URL: String = "https://10.0.2.2:5001/"
var BASE_URL: String = "https://35.187.60.212/"

/*
 * Deze methode dient om een Retrofit client op te stellen dat HTTP requesten opstelt en verwerkt.
 */

fun getClient(): ApiService {
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
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
    fun getPlatforms(): Call<List<Platform>>

 /*   //USERCONTROLLER
    @FormUrlEncoded
    @POST("api/User/Login")
    fun userLogin(
        @Field("Email")email:String,
        @Field("Password")passwd:String

    ):Call<User>*/

    //PROJECTCONTROLLER
    //GET PROJECT BY ID
    @GET("api/project/GetById")
    fun getById(@Query("projectId") projectId: Int): Call<Project>

    //GET SORTED PROJECTS BY PLATFORM ID
    @GET("api/project/SortedBy")
    fun sortedBy(@Query("quota") quota: Int, @Query("platformId") platformId: Int): Call<List<Project>>

    //MODULECONTROLLER
    //GET list all the modules of one project
    @GET("api/module/GetModules")
    fun getModules(@Query("projectId") projectId: Int): Call<List<Module>>


    @GET("api/module/GetQuestionnaire")
    fun getQuestionnaire(@Query("projectId") projectId: Int, @Query("phaseId") phaseId: Int): Call<Questionnaire>

    @GET("api/module/GetIdeation")
    fun getIdeation(@Query("projectId") projectId: Int, @Query("phaseId") phaseId: Int): Call<Ideation>

    @GET("api/module/GetModuleForPhase")
    fun getModuleForPhase(@Query("phaseId") phaseId: Int): Call<Module>


    @GET("api/module/GetIdeations")
    fun getIdeations(@Query("projectId") projectId:Int): Call<List<Ideation>>


    @GET("api/module/GetQuestionnaires")
    fun getQuestionnaires(@Query("projectId") projectId:Int): Call<List<Questionnaire>>
    // TODO() POST AND PUT

    @GET("api/module/GetIdeationQuestions")
    fun getIdeationQuestions(@Query("moduleId") moduleId: Int): Call<List<IdeationQuestion>>

    @GET("api/module/GetIdeas")
    fun getIdeas(@Query("ideationQuestionId") ideationQuestionId: Int): Call<List<Idea>>
}
/*
 * Deze methode wordt bij de splash opgeroepen om te testen of er al dan niet een verbinding kan worden vastgesteld met
 * de server.
 */

fun isConnectedToServer(context: Context): Boolean {
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