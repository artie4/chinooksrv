package org.artie4.chinooksrv.mapper

import org.artie4.chinooksrv.dto.AlbumDto
import org.artie4.chinooksrv.entity.Album
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [TrackMapper::class],
)
interface AlbumMapper {
    fun toAlbumDto(album: Album): AlbumDto
}
