package org.artie4.chinooksrv.mapper

import com.netflix.dgs.codegen.generated.types.TrackOutput
import org.artie4.chinooksrv.dto.TrackDto
import org.artie4.chinooksrv.entity.Track
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
interface TrackMapper {
    fun toTrackDto(track: Track): TrackDto

    fun toTrackOutput(trackDto: TrackDto): TrackOutput
}
