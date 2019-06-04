package com.spanishinquisition.treecompany.models.fields

import com.google.gson.annotations.SerializedName
import com.spanishinquisition.treecompany.models.Idea

/*
 *  @author David Matei
 */

data class ImageField(
    @SerializedName("id") val id: Int,
    @SerializedName("text") val text: String,
    @SerializedName("textLength") val textLength: Int,
    @SerializedName("idea") val idea: Idea,
    @SerializedName("multipleChoice") var multipleChoice: Boolean,
    @SerializedName("options") var options: List<String>
)