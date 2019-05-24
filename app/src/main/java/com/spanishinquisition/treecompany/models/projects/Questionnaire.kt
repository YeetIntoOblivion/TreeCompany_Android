package com.spanishinquisition.treecompany.models.projects

import com.google.gson.annotations.SerializedName
import com.spanishinquisition.treecompany.models.projects.Module

/*
 *  @author David Matei
 */

data class Questionnaire (
    @SerializedName("userCount") val userCount: Int,
    @SerializedName("questions") val questions: String
)




