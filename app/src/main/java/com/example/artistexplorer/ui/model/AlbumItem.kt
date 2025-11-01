package com.example.artistexplorer.ui.model

data class AlbumItem(
    val id: String,
    val title: String,
    val description: String,
    val year: String,
    val genre: String?,
    val albumCover: String?,
    val tracks: List<TrackItem>
)