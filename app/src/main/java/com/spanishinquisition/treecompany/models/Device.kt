package com.spanishinquisition.treecompany.models

import com.google.gson.annotations.SerializedName

data class Device(


    @SerializedName("id") val id: Int,
    @SerializedName("locationX") val locationX: Int,
    @SerializedName("locationY") val locationY: Int

)