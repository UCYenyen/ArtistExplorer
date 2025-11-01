package com.example.artistexplorer.ui.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.artistexplorer.ui.model.ArtistItem

@Composable
fun ArtistCard(artistData : ArtistItem) {
    Box(
        modifier = Modifier
            .height(380.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Gray.copy(alpha = 0.7f), RoundedCornerShape(14.dp))
    ) {
        AsyncImage(
            model = artistData.image,
            contentDescription = "${artistData.name} album cover",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(14.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(artistData.name, fontSize = 24.sp, fontWeight = FontWeight.Normal, color = Color(0xFFAEAC96))
            Text(artistData.genre ?: "Unknown", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color(0xFFAEAC96))
        }
    }
}
