package com.example.artistexplorer.ui.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AlbumCardDetail(
    modifier: Modifier = Modifier,
    imageUrl: String,
    albumName: String,
    releaseYear: String,
    genre: String,
    description: String
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .border(color = Color.Gray.copy(alpha = 0.4f), width = 1.dp, shape = MaterialTheme.shapes.medium)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Album cover for $albumName",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF212121))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = albumName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "$releaseYear • $genre",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f),
                        lineHeight = MaterialTheme.typography.bodySmall.lineHeight * 1.5
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun AlbumCardDetailPreview() {
    val dummyImageUrl = "https://example.com/sob_rock.jpg"
    val dummyAlbumName = "Sob Rock"
    val dummyReleaseYear = "2021"
    val dummyGenre = "Indie"
    val dummyDescription = "Sob Rock is the eighth studio album by American singer-songwriter John Mayer, released on July 16, 2021, by Columbia Records. The single \"New Light\", released in May 2018, is included on the album, as are Mayer's two singles from 2019, \"I Guess I Just Feel Like\" and \"Carry Me Away\". The lead single \"Last Train Home\" was released on June 4, 2021, and features guest vocals from Maren Morris."

    AlbumCardDetail(
        imageUrl = dummyImageUrl,
        albumName = dummyAlbumName,
        releaseYear = dummyReleaseYear,
        genre = dummyGenre,
        description = dummyDescription
    )
}