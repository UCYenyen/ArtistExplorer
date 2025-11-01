package com.example.artistexplorer.ui.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.artistexplorer.ui.model.AlbumItem

@Composable
fun AlbumDetailSuccessView(
    album: AlbumItem
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            AlbumCardDetail(
                modifier = Modifier.padding(16.dp),
                imageUrl = album.albumCover ?: "",
                albumName = album.title,
                releaseYear = album.year,
                genre = album.genre ?: "",
                description = album.description
            )
        }

        item {
            Text("Tracks", modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp), color = Color(0xFFD8C74B))
        }

        itemsIndexed(album.tracks) { index, track ->
            TrackItem(
                trackItem = track,
                trackNumber = index + 1
            )
        }
    }
}
