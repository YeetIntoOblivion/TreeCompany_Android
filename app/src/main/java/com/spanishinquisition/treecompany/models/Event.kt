package com.spanishinquisition.treecompany.models

import com.google.gson.annotations.SerializedName


data class Event (

    @SerializedName("id") val id : Int,
    @SerializedName("organisation") val organisation : String,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("startDate") val startDate : String,
    @SerializedName("endDate") val endDate : String
)