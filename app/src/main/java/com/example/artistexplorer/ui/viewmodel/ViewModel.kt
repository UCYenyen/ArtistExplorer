package com.example.artistexplorer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artistexplorer.data.container.ServerContainer
import com.example.artistexplorer.ui.uistates.AlbumUIState
import com.example.artistexplorer.ui.uistates.ArtistUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    private val repository = ServerContainer().weatherServerRepository
    private val _artistUIState = MutableStateFlow<ArtistUIState>(ArtistUIState.Initial)
    val artistUIState: StateFlow<ArtistUIState> = _artistUIState.asStateFlow()

    private val _albumUIState = MutableStateFlow<AlbumUIState>(AlbumUIState.Initial)
    val albumUIState: StateFlow<AlbumUIState> = _albumUIState.asStateFlow()

    fun getArtistData() {
        viewModelScope.launch {
            _artistUIState.value = ArtistUIState.Loading
            try {
                val result = repository.getArtist()
                if (result == null) {
                    _artistUIState.value = ArtistUIState.Error("Artist not found")
                    return@launch
                }
                _artistUIState.value = ArtistUIState.Success(result)
            } catch (e: Exception) {
                if(e.message == "timeout" || e.message == "Unable to resolve host \"www.theaudiodb.com\": No address associated with hostname"){
                    _artistUIState.value = ArtistUIState.Error("Error: Tidak ada koneksi internet")
                    return@launch
                }
                _artistUIState.value = ArtistUIState.Error(e.message ?: "Tidak ada koneksi internet")
            }
        }
    }

    fun fetchAlbum(albumId: String) {
        viewModelScope.launch {
            _albumUIState.value = AlbumUIState.Loading
            try {
                val result = repository.getAlbumDetails(albumId)
                if (result == null) {
                    _albumUIState.value = AlbumUIState.Error("Artist not found")
                    return@launch
                }
                _albumUIState.value = AlbumUIState.Success(result)
            } catch (e: Exception) {
                _albumUIState.value = AlbumUIState.Error(e.message ?: "Tidak ada koneksi internet")
            }
        }
    }
}
