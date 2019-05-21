package com.spanishinquisition.treecompany.models.fields

import com.google.gson.annotations.SerializedName
import com.spanishinquisition.treecompany.models.Idea

data class ClosedField(

    @SerializedName("id") val id: Int,
    @SerializedName("text") val text: String,
    @SerializedName("textLength") val textLength: Int,
    @SerializedName("idea") val idea: Idea,

    @SerializedName("Url") var url: String
)