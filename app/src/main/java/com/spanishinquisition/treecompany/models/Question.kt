package com.spanishinquisition.treecompany.models

import com.google.gson.annotations.SerializedName


data class Questions (

    @SerializedName("questionType") val questionType : Int,
    @SerializedName("optional") val optional : Boolean,
    @SerializedName("questionnaire") val questionnaire : String,
    @SerializedName("answers") val answers : String,
    @SerializedName("id") val id : Int,
    @SerializedName("questionText") val questionText : String,
    @SerializedName("module") val module : Module
)