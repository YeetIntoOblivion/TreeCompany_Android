package com.spanishinquisition.treecompany.models

data class  Module (
    // TODO aanvullen missende attributen
    val id: Int,
    val project: Project,
    val parentPhase: Phase,
    val title: String,
    val onGoing: Boolean,
    val likeCount: Int,
    val fbLikeCount: Int,
    val twitterLikeCount: Int,
    val shareCount: Int,
    val retweetCount: Int,
    val moduleType: ModuleType,
    val phases: List<Phase>,
    val tags: List<String>
)