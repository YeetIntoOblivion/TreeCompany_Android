package com.spanishinquisition.treecompany.models

data class Platform(
    val id: Int,
    val name: String,
    val url: String,
    val owners: List<User>,
    val users: List<User>
)