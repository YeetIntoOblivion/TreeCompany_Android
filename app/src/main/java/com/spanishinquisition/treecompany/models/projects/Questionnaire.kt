package com.spanishinquisition.treecompany.models.projects

import com.google.gson.annotations.SerializedName
import com.spanishinquisition.treecompany.models.ModuleType
import com.spanishinquisition.treecompany.models.projects.Module

data class Questionnaire ( //TODO() INHERIT FROM MODULE

    @SerializedName("id") val id: Int,
    @SerializedName("project") val project: Project,
    @SerializedName("parentPhase") val parentPhase: Phase,
    @SerializedName("title") val title: String,
    @SerializedName("onGoing") val onGoing: Boolean,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("fbLikeCount") val fbLikeCount: Int,
    @SerializedName("twitterLikeCount") val twitterLikeCount: Int,
    @SerializedName("shareCount") val shareCount: Int,
    @SerializedName("retweetCount") val retweetCount: Int,
    @SerializedName("voteLevel") val voteLevel: Int,
    @SerializedName("moduleType") val moduleType: ModuleType,
    @SerializedName("phases") val phases: List<Phase>,
    @SerializedName("tags") val tags: List<String>,

    @SerializedName("userCount") val userCount: Int,
    @SerializedName("questions") val questions: String
)




