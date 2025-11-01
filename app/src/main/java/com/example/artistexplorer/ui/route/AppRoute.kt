package com.example.artistexplorer.ui.route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.artistexplorer.ui.uistates.AlbumUIState
import com.example.artistexplorer.ui.uistates.ArtistUIState
import com.example.artistexplorer.ui.view.AlbumView
import com.example.artistexplorer.ui.view.ArtistView
import com.example.artistexplorer.ui.viewmodel.ViewModel

enum class AppView(val title: String) {
    Artist("Artist Explorer"),
    Album("Album Detail"),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoute() {
    val navController = rememberNavController()
    val viewModel: ViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val artistUiState by viewModel.artistUIState.collectAsState()
    val albumUiState by viewModel.albumUIState.collectAsState()

    val currentRoute = navBackStackEntry?.destination?.route
    val canNavigateBack = navController.previousBackStackEntry != null

    val title = when {
        currentRoute?.startsWith(AppView.Album.name) == true -> {
            when (val state = albumUiState) {
                is AlbumUIState.Success -> state.data.title
                is AlbumUIState.Loading -> "Loading..."
                is AlbumUIState.Error -> "Error"
                is AlbumUIState.Initial -> "Loading..."
            }
        }
        else -> {
            when (val state = artistUiState) {
                is ArtistUIState.Success -> state.data.name
                is ArtistUIState.Loading -> "Loading..."
                is ArtistUIState.Error -> "Error"
                is ArtistUIState.Initial -> "Loading..."
            }
        }
    }

    Scaffold(
        topBar = {
            TopNavbar(
                title = title,
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = AppView.Artist.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(AppView.Artist.name) {
                ArtistView(
                    viewModel = viewModel,
                    onViewAlbum = { albumId ->
                        navController.navigate("${AppView.Album.name}/$albumId")
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(
                route = "${AppView.Album.name}/{albumId}",
                arguments = listOf(
                    navArgument("albumId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                AlbumView(
                    id = backStackEntry.arguments?.getString("albumId")!!,
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavbar(
    title: String,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF1C2021),
            titleContentColor = Color(0xFFABA69B),
            navigationIconContentColor = Color.White
        ),
    )
}