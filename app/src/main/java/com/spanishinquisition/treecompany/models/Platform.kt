package com.spanishinquisition.treecompany.models

data class Platform(
    // TODO aanvullen missende attributen
    val id: Int,
    val name: String,
    val url: String,
    val owners: List<UimvcUser>,
    val users: List<UimvcUser>
)