package com.example.artistexplorer.ui.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.artistexplorer.ui.model.AlbumItem
import com.example.artistexplorer.ui.model.ArtistItem

@Composable
fun ArtistSuccessView(onViewAlbum: (id: String) -> Unit, albumItem: List<AlbumItem>, artistData: ArtistItem) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        ArtistCard(artistData)
        AlbumCardPreviewList(
            albums = albumItem,
            onAlbumClick = onViewAlbum
        )
    }
}