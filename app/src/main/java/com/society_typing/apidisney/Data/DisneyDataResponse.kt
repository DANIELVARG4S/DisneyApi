package com.society_typing.apidisney.Data

import com.google.gson.annotations.SerializedName

data class DisneyDataResponse(

    @SerializedName("data") val result: List<DisneyItemResponse>,
    @SerializedName("count")  val count: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("nextPage") val nextPage: String?
)

data class DisneyItemResponse(
    @SerializedName("_id") val _id: Int,
    @SerializedName("name") val name:String,
    @SerializedName("imageUrl") val imageUrl:String,
    @SerializedName("url") val url:String
)
