package com.spanishinquisition.treecompany.models.projects

import com.google.gson.annotations.SerializedName
import com.spanishinquisition.treecompany.models.projects.Module

data class Questionnaire ( //TODO() INHERIT FROM MODULE
    @SerializedName("userCount") val userCount: Int,
    @SerializedName("questions") val questions: String
)




