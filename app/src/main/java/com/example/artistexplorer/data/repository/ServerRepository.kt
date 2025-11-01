package com.example.artistexplorer.data.repository

import com.example.artistexplorer.data.service.ServerService
import com.example.artistexplorer.ui.model.AlbumItem
import com.example.artistexplorer.ui.model.ArtistItem
import com.example.artistexplorer.ui.model.TrackItem

class ServerRepository(private val service: ServerService, private val artistName: String) {
    suspend fun getArtist(): ArtistItem? {
        val artistRes = service.searchArtist(artistName)
        if (!artistRes.isSuccessful) {
            return null
        }
        val artist = artistRes.body()!!.artists[0]

        val artistAlbumRes = service.searchAlbum(artistName)
        if (!artistAlbumRes.isSuccessful) {
            return null
        }
        val albums = artistAlbumRes.body()!!.album

        return ArtistItem(
            name = artistName,
            genre = artist.strGenre,
            image = artist.strArtistThumb,
            albums = albums.sortedByDescending { it.intYearReleased }.map {
                AlbumItem(
                    id = it.idAlbum,
                    title = it.strAlbum,
                    year = it.intYearReleased,
                    genre = it.strGenre,
                    description = it.strDescriptionEN ?: "",
                    albumCover = it.strAlbumThumb,
                    tracks = emptyList()
                )
            }
        )
    }

    suspend fun getAlbumDetails(albumId: String): AlbumItem? {
        val albumRes = service.getAlbumDetail(albumId)
        if (!albumRes.isSuccessful) {
            return null
        }
        val albumDetail = albumRes.body()!!.album[0]

        val trackRes = service.getAlbumTrack(albumId)
        if (!trackRes.isSuccessful) {
            return null
        }
        val albumTrack = trackRes.body()!!.track

        return AlbumItem(
            id = albumDetail.idAlbum,
            title = albumDetail.strAlbum,
            year = albumDetail.intYearReleased,
            genre = albumDetail.strGenre,
            description = albumDetail.strDescriptionEN ?: "",
            albumCover = albumDetail.strAlbumThumb,
            tracks = albumTrack.map {
                TrackItem(
                    title = it.strTrack,
                    duration = "%d:%02d".format(it.intDuration.toInt() / 60000, (it.intDuration.toInt() / 1000) % 60)
                )
            }
        )
    }
}