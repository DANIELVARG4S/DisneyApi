package com.society_typing.apidisney.Service

import com.society_typing.apidisney.Data.DisneyDataResponse
import com.society_typing.apidisney.Data.DisneyDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacterDisney(@Query("name") characterDisney: String):Response<DisneyDataResponse>


    @GET("character/{id}")
    suspend fun getDisneyDetail(@Path("id") characterId : Int): Response<DisneyDetailResponse>
}