package com.spanishinquisition.treecompany.models

import com.google.gson.annotations.SerializedName


data class Idea(

    @SerializedName("id") val id: Int,
    @SerializedName("ideaQuestion") val ideaQuestion: String,
    @SerializedName("user") val user: String,
    @SerializedName("reported") val reported: Boolean,
    @SerializedName("reviewByAdmin") val reviewByAdmin: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("retweetCount") val retweetCount: Int,
    @SerializedName("shareCount") val shareCount: Int,
    @SerializedName("visible") val visible: Boolean,
    @SerializedName("voteCount") val voteCount: Int,
    @SerializedName("status") val status: String,
    @SerializedName("parentIdea") val parentIdea: String,
    @SerializedName("verifiedUser") val verifiedUser: Boolean,
    @SerializedName("device") val device: String,
    @SerializedName("isDeleted") val isDeleted: Boolean,
    @SerializedName("field") val field: String,
    @SerializedName("cfield") val cfield: String,
    @SerializedName("ifield") val ifield: String,
    @SerializedName("vfield") val vfield: String,
    @SerializedName("mfield") val mfield: String

)