package org.artie4.chinooksrv.stub

import org.artie4.chinooksrv.dto.AlbumDto
import org.artie4.chinooksrv.dto.TrackDto
import org.artie4.chinooksrv.entity.Album
import org.artie4.chinooksrv.entity.Artist
import org.artie4.chinooksrv.entity.Track

object AlbumStub {
    fun getAlbumEntity(
        id: Long = 345,
        title: String = "title",
        artist: Artist? = null,
        tracks: MutableSet<Track> = mutableSetOf(),
    ): Album =
        Album().apply {
            this.id = id
            this.title = title
            this.artist = artist
            this.tracks = tracks
        }

    fun getAlbumDto(
        id: Long = 345,
        title: String = "title",
        tracks: MutableSet<TrackDto> = mutableSetOf(),
    ): AlbumDto =
        AlbumDto(
            id = id,
            title = title,
            tracks = tracks,
        )
}
