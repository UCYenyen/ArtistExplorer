package com.example.artistexplorer.data.container

import com.example.artistexplorer.data.repository.ServerRepository
import com.example.artistexplorer.data.service.ServerService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerContainer {
    companion object {
        const val BASE_URL = "https://www.theaudiodb.com/api/v1/json/123/"
        const val ARTIST_NAME = "John Mayer"
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: ServerService by lazy {
        retrofit.create(ServerService::class.java)
    }

    val weatherServerRepository : ServerRepository by lazy {
        ServerRepository(retrofitService, ARTIST_NAME)
    }
}