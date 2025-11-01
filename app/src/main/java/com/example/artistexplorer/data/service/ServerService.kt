package com.example.artistexplorer.data.service

import com.example.artistexplorer.data.dto.DetailAlbumResponse
import com.example.artistexplorer.data.dto.SearchAlbumResponse
import com.example.artistexplorer.data.dto.SearchResponse
import com.example.artistexplorer.data.dto.TrackAlbumResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerService {
    @GET("search.php")
    suspend fun searchArtist(
        @Query("s") artistName: String
    ): Response<SearchResponse>

    @GET("searchalbum.php")
    suspend fun searchAlbum(
        @Query("s") artistName: String
    ): Response<SearchAlbumResponse>

    @GET("album.php")
    suspend fun getAlbumDetail(
        @Query("m") albumId: String
    ): Response<DetailAlbumResponse>

    @GET("track.php")
    suspend fun getAlbumTrack(
        @Query("m") albumId: String
    ): Response<TrackAlbumResponse>
}