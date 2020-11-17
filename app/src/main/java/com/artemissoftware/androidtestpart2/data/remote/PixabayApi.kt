package com.artemissoftware.androidtestpart2.data.remote

import com.artemissoftware.androidtestpart2.BuildConfig
import com.artemissoftware.androidtestpart2.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun searchForImage(@Query("q") searchQuery: String, @Query("key") apiKey: String = BuildConfig.API_KEY): Response<ImageResponse>
}