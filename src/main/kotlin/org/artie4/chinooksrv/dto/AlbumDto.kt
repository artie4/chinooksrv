package org.artie4.chinooksrv.dto

/**
 * DTO for {@link org.artie4.chinooksrv.entity.Album}
 */
data class AlbumDto(
    val id: Long,
    val title: String,
    val tracks: MutableSet<TrackDto> = mutableSetOf(),
)
