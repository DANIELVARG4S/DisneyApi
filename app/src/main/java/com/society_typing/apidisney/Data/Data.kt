package com.society_typing.apidisney.Data

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("__v") val v: Int,
    @SerializedName("_id") val id: Int,
    val allies: List<Any>,
    val createdAt: String,
    val enemies: List<Any>,
    val films: List<Any>,
    val imageUrl: String,
    val name: String,
    val parkAttractions: List<Any>,
    val shortFilms: List<Any>,
    val sourceUrl: String,
    val tvShows: List<String>,
    val updatedAt: String,
    val url: String,
    val videoGames: List<Any>
)
