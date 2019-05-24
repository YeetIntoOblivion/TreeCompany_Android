package com.spanishinquisition.treecompany.models.projects
import com.google.gson.annotations.SerializedName
import com.spanishinquisition.treecompany.models.Platform
import com.spanishinquisition.treecompany.models.User

/*
 *  @author David Matei
 */

data class Project (
	@SerializedName("id") val id : Int,
	@SerializedName("platform") val platform : Platform?,
	@SerializedName("user") val user : User?,
	@SerializedName("title") val title : String?,
	@SerializedName("goal") val goal : String?,
	@SerializedName("status") val status : String?,
	@SerializedName("visible") val visible : Boolean?,
	@SerializedName("reactionCount") val reactionCount : Int?,
	@SerializedName("likeCount") val likeCount : Int?,
	@SerializedName("fbLikeCount") val fbLikeCount : Int?,
	@SerializedName("twitterLikeCount") val twitterLikeCount : Int?,
	@SerializedName("likeVisibility") val likeVisibility : Int?,
	@SerializedName("currentPhase") val currentPhase : Phase?,
	@SerializedName("phases") val phases : List<Phase>,
	@SerializedName("previewImages") val previewImages : String?,
	@SerializedName("modules") val modules : String?
)