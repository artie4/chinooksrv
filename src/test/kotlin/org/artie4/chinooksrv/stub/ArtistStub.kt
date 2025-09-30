package org.artie4.chinooksrv.stub

import org.artie4.chinooksrv.dto.AlbumDto
import org.artie4.chinooksrv.dto.ArtistDto
import org.artie4.chinooksrv.entity.Album
import org.artie4.chinooksrv.entity.Artist

object ArtistStub {
    fun getArtistEntity(
        id: Long? = 123,
        name: String? = "Unknown Artist",
        albums: MutableSet<Album> = mutableSetOf(),
    ): Artist =
        Artist()
            .apply {
                this.id = id
                this.name = name
                this.albums = albums
            }

    fun getArtistDto(
        id: Long = 123,
        name: String = "Unknown Artist",
        albums: MutableSet<AlbumDto> = mutableSetOf(),
    ): ArtistDto =
        ArtistDto(
            id = id,
            name = name,
            albums = albums,
        )
}
