package com.spanishinquisition.treecompany.models

import com.google.gson.annotations.SerializedName

data class Ideation(

    @SerializedName("user") val user : String,
    @SerializedName("userIdea") val userIdea : Boolean,
    @SerializedName("event") val event : String,
    @SerializedName("mediaLink") val mediaLink : String,
    @SerializedName("extraInfo") val extraInfo : String,
    @SerializedName("requiredFields") val requiredFields : Int,
    @SerializedName("centralQuestions") val centralQuestions : String,
    @SerializedName("id") val id : Int,
    @SerializedName("project") val project : String,
    @SerializedName("parentPhase") val parentPhase : String,
    @SerializedName("title") val title : String,
    @SerializedName("onGoing") val onGoing : Boolean,
    @SerializedName("likeCount") val likeCount : Int,
    @SerializedName("fbLikeCount") val fbLikeCount : Int,
    @SerializedName("twitterLikeCount") val twitterLikeCount : Int,
    @SerializedName("shareCount") val shareCount : Int,
    @SerializedName("retweetCount") val retweetCount : Int,
    @SerializedName("voteLevel") val voteLevel : Int,
    @SerializedName("moduleType") val moduleType : Int,
    @SerializedName("phases") val phases : String,
    @SerializedName("tags") val tags : String
)
