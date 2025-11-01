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
import com.example.artistexplorer.ui.uistates.AlbumUIState
import com.example.artistexplorer.ui.view.components.AlbumDetailSuccessView
import com.example.artistexplorer.ui.viewmodel.ViewModel

@Composable
fun AlbumView(
    viewModel: ViewModel = viewModel(),
    id: String,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    LaunchedEffect(id) {
        viewModel.fetchAlbum(id)
    }

    val albumUiState by viewModel.albumUIState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF282828)),
        contentAlignment = Alignment.Center
    ) {
        when (val state = albumUiState) {
            is AlbumUIState.Loading -> {
                CircularProgressIndicator(color = Color(0xFFF5C144))
            }
            is AlbumUIState.Error -> {
                Text(text = state.message, color = Color(0xFF9C3527), textAlign = TextAlign.Center, fontSize = 14.sp)
            }
            is AlbumUIState.Success -> {
                AlbumDetailSuccessView(state.data)
            }
            is AlbumUIState.Initial -> {
                CircularProgressIndicator(color = Color(0xFFF5C144))
            }
        }
    }
}