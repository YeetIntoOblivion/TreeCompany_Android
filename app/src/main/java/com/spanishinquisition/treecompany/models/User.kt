package com.spanishinquisition.treecompany.models

import com.google.gson.annotations.SerializedName


data class User (

	@SerializedName("name") val name : String,
	@SerializedName("zipcode") val zipcode : String,
	@SerializedName("gender") val gender : Boolean,
	@SerializedName("dateOfBirth") val dateOfBirth : String,
	@SerializedName("platformDetails") val platformDetails : Int,
	@SerializedName("orgName") val orgName : String,
	@SerializedName("description") val description : String,
	@SerializedName("banned") val banned : Boolean,
	@SerializedName("active") val active : Boolean,
	@SerializedName("id") val id : Int,
	@SerializedName("userName") val userName : String,
	@SerializedName("normalizedUserName") val normalizedUserName : String,
	@SerializedName("email") val email : String,
	@SerializedName("normalizedEmail") val normalizedEmail : String,
	@SerializedName("emailConfirmed") val emailConfirmed : Boolean,
	@SerializedName("passwordHash") val passwordHash : String,
	@SerializedName("securityStamp") val securityStamp : String,
	@SerializedName("concurrencyStamp") val concurrencyStamp : String,
	@SerializedName("phoneNumber") val phoneNumber : String,
	@SerializedName("phoneNumberConfirmed") val phoneNumberConfirmed : Boolean,
	@SerializedName("twoFactorEnabled") val twoFactorEnabled : Boolean,
	@SerializedName("lockoutEnd") val lockoutEnd : String,
	@SerializedName("lockoutEnabled") val lockoutEnabled : Boolean,
	@SerializedName("accessFailedCount") val accessFailedCount : Int
)