package com.example.artistexplorer.ui.model

data class ArtistItem(
    val name: String,
    val image: String,
    val genre: String?,
    val albums: List<AlbumItem>
)