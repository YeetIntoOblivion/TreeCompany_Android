package com.spanishinquisition.treecompany.models.projects

import com.google.gson.annotations.SerializedName
import com.spanishinquisition.treecompany.models.Event
import com.spanishinquisition.treecompany.models.User

data class Ideation( //TODO() INHERIT FROM MODULE

    @SerializedName("user") val user : User,
    @SerializedName("userIdea") val userIdea : Boolean,
    @SerializedName("event") val event : Event,
    @SerializedName("mediaLink") val mediaLink : String,
    @SerializedName("extraInfo") val extraInfo : String,
    @SerializedName("requiredFields") val requiredFields : Int,
    @SerializedName("centralQuestions") val centralQuestions : List<IdeationQuestion>
)
