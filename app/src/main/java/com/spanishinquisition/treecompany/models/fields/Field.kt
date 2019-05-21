package com.spanishinquisition.treecompany.models

import com.google.gson.annotations.SerializedName

data class Field (

    @SerializedName("id") val id : Int,
    @SerializedName("text") val text : String,
    @SerializedName("textLength") val textLength : Int,
    @SerializedName("idea") val idea : Idea
)