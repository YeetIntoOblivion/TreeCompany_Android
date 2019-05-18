package com.spanishinquisition.treecompany.models

data class UimvcUser (
    // TODO aanvullen missende attributen
    val name: String,
    val zipcode: String,
    val gender: Boolean,
    val dateOfBirth: String,
    val platformDetails: Int,
    val orgName: String,
    val description: String,
    val banned: Boolean,
    val active: Boolean
)