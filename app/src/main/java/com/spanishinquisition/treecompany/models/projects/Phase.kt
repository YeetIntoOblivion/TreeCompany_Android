package com.spanishinquisition.treecompany.models.projects

import com.google.gson.annotations.SerializedName


data class Phase (

	@SerializedName("id") val id : Int,
	@SerializedName("project") val project : Project,
	@SerializedName("module") val module : Module?,
	@SerializedName("description") val description : String?,
	@SerializedName("startDate") val startDate : String?,
	@SerializedName("endDate") val endDate : String?
)