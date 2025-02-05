package org.artie4.chinooksrv.mapper

import org.artie4.chinooksrv.dto.ArtistDto
import org.artie4.chinooksrv.entity.Artist
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [AlbumMapper::class],
)
interface ArtistMapper {
    fun toArtistDto(artist: Artist): ArtistDto
}
