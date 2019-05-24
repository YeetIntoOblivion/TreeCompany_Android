package com.spanishinquisition.treecompany.models

import com.google.gson.annotations.SerializedName

/*
 *  @author David Matei
 */

data class Device(
    @SerializedName("id") val id: Int,
    @SerializedName("locationX") val locationX: Int,
    @SerializedName("locationY") val locationY: Int
)