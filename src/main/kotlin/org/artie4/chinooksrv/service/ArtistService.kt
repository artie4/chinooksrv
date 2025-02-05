package org.artie4.chinooksrv.service

import org.artie4.chinooksrv.dto.ArtistDto

interface ArtistService {
    fun getArtistById(id: Long): ArtistDto

    fun getArtistByName(name: String): List<ArtistDto>

    fun getArtistByAlbumTitle(albumTitle: String): List<ArtistDto>
}
