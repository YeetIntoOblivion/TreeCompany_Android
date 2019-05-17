package com.spanishinquisition.treecompany.models

data class Project(
    // TODO aanvullen missende attributen
    val id: Int,
    val title: String,
    val goal: String,
    val status: String,
    val visible: Boolean,
    val reactionCount: Int,
    val likeCount: Int,
    val fbLikeCount: Int,
    val twitterLikeCount: Int,
    val likeVisibility: Int
)