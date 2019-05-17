package com.spanishinquisition.treecompany.models

import com.google.gson.annotations.SerializedName

data class IdeationQuestion (

    @SerializedName("description") val description : String,
    @SerializedName("siteUrl") val siteUrl : String,
    @SerializedName("questionTitle") val questionTitle : String,
    @SerializedName("ideation") val ideation : Ideation,
    @SerializedName("ideas") val ideas : String,
    @SerializedName("acceptedAnswerTypes") val acceptedAnswerTypes : String,
    @SerializedName("id") val id : Int,
    @SerializedName("questionText") val questionText : String,
    @SerializedName("module") val module : String
)