package com.spanishinquisition.treecompany.models

import com.google.gson.annotations.SerializedName
import com.spanishinquisition.treecompany.models.fields.*
 import com.spanishinquisition.treecompany.models.projects.IdeationQuestion


data class Idea(

    @SerializedName("id") val id: Int,
    @SerializedName("ideaQuestion") val ideaQuestion: IdeationQuestion?,
    @SerializedName("user") val user: User?,
    @SerializedName("reported") val reported: Boolean?,
    @SerializedName("reviewByAdmin") val reviewByAdmin: Boolean?,
    @SerializedName("title") val title: String?,
    @SerializedName("retweetCount") val retweetCount: Int?,
    @SerializedName("shareCount") val shareCount: Int?,
    @SerializedName("visible") val visible: Boolean?,
    @SerializedName("voteCount") val voteCount: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("parentIdea") val parentIdea: Idea?,
    @SerializedName("verifiedUser") val verifiedUser: Boolean?,
    @SerializedName("device") val device: Device?,
    @SerializedName("isDeleted") val isDeleted: Boolean?,
    @SerializedName("field") val field: Field?,
    @SerializedName("cfield") val cfield: ClosedField?,
    @SerializedName("ifield") val ifield: ImageField?,
    @SerializedName("vfield") val vfield: VideoField?,
    @SerializedName("mfield") val mfield: MapField?

)