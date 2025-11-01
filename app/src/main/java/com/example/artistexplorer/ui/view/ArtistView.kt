package com.example.artistexplorer.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.artistexplorer.ui.uistates.ArtistUIState
import com.example.artistexplorer.ui.view.components.ArtistSuccessView
import com.example.artistexplorer.ui.viewmodel.ViewModel

@Composable
fun ArtistView(
    viewModel: ViewModel = viewModel(),
    onViewAlbum: (id: String) -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.getArtistData()
    }

    val artistUiState by viewModel.artistUIState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF282828)),
        contentAlignment = Alignment.Center
    ) {
        when (val state = artistUiState) {
            is ArtistUIState.Loading -> {
                CircularProgressIndicator(color = Color(0xFFF5C144))
            }

            is ArtistUIState.Error -> {
                Text(text = state.message, color = Color(0xFF9C3527), textAlign = TextAlign.Center, fontSize = 14.sp)
            }

            is ArtistUIState.Success -> {
                ArtistSuccessView(
                    albumItem = state.data.albums,
                    onViewAlbum = onViewAlbum, artistData = state.data
                )
            }

            is ArtistUIState.Initial -> {
                CircularProgressIndicator(color = Color(0xFFF5C144))
            }
        }
    }
}