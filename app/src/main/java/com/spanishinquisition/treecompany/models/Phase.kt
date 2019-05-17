package com.spanishinquisition.treecompany.models

import com.google.gson.annotations.SerializedName


data class Phase (

	@SerializedName("id") val id : Int,
	@SerializedName("project") val project : String,
	@SerializedName("module") val module : String,
	@SerializedName("description") val description : String,
	@SerializedName("startDate") val startDate : String,
	@SerializedName("endDate") val endDate : String
)