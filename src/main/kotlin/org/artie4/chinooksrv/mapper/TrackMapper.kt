package org.artie4.chinooksrv.mapper

import org.artie4.chinooksrv.dto.TrackDto
import org.artie4.chinooksrv.entity.Track
import org.mapstruct.Mapper

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
interface TrackMapper {
    fun toTrackDto(track: Track): TrackDto
}
