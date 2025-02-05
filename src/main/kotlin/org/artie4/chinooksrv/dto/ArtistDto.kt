package org.artie4.chinooksrv.dto

/**
 * DTO for {@link org.artie4.chinooksrv.entity.Artist}
 */
data class ArtistDto(
    val id: Long,
    val name: String,
    val albums: MutableSet<AlbumDto> = mutableSetOf(),
)
