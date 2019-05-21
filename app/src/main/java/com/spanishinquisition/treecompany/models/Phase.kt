package com.spanishinquisition.treecompany.models

data class Phase (
    // TODO aanvullen missende attributen
    val id: Int,
    val project: Project,
    val module: Module,
    val description: String,
    val startDate: String,
    val endDate: String
)