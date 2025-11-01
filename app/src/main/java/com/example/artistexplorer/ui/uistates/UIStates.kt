package com.example.artistexplorer.ui.uistates

import com.example.artistexplorer.ui.model.AlbumItem
import com.example.artistexplorer.ui.model.ArtistItem

sealed interface AlbumUIState {
    object Initial : AlbumUIState
    object Loading : AlbumUIState
    data class Success(val data: AlbumItem) : AlbumUIState
    data class Error(val message: String) : AlbumUIState
}

sealed interface ArtistUIState {
    object Initial : ArtistUIState
    object Loading : ArtistUIState
    data class Success(val data: ArtistItem) : ArtistUIState
    data class Error(val message: String) : ArtistUIState
}