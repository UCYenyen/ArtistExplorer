package com.example.artistexplorer.ui.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artistexplorer.ui.model.AlbumItem

@Composable
fun AlbumCardPreviewList(
    albums: List<AlbumItem>,
    modifier: Modifier = Modifier,
    onAlbumClick: (albumId: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(albums) { album ->
            AlbumCard(
                album = album,
                onClick = { onAlbumClick(album.id) }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun AlbumCardPreviewListPreview() {
    val dummyAlbums = remember {
        listOf(
            AlbumItem(
                id = "1",
                albumCover = "https://example.com/sob_rock.jpg",
                title = "Sob Rock",
                description = "2021 • Indie",
                year = "2021",
                genre = "Indie",
                tracks = emptyList()
            ),
            AlbumItem(
                id = "2",
                albumCover = "https://example.com/new_light.jpg",
                title = "New Light",
                description = "2018 • Indie",
                year = "2018",
                genre = "Indie",
                tracks = emptyList()
            )
        )
    }

    AlbumCardPreviewList(
        albums = dummyAlbums,
        onAlbumClick = {}
    )
}