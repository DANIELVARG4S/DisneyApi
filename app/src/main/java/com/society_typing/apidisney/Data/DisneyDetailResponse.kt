package com.society_typing.apidisney.Data

import com.google.gson.annotations.SerializedName

data class DisneyDetailResponse(
    @SerializedName("_id") val _id: Int,
    @SerializedName("name") val name:String,
    @SerializedName("imageUrl") val imageUrl:String,
    @SerializedName("url") val url:String
)