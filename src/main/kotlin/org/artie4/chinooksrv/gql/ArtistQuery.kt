package org.artie4.chinooksrv.gql

import com.netflix.dgs.codegen.generated.DgsConstants
import com.netflix.dgs.codegen.generated.types.AlbumOutput
import com.netflix.dgs.codegen.generated.types.ArtistOutput
import com.netflix.dgs.codegen.generated.types.ArtistSearchFilter
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import org.artie4.chinooksrv.service.ArtistService

@DgsComponent
class ArtistQuery(
    private val artistService: ArtistService,
) {
    @DgsQuery(field = DgsConstants.QUERY.GetArtistsByFilter)
    fun getArtists(
        @InputArgument filter: ArtistSearchFilter,
    ): List<ArtistOutput> {
        return artistService.findArtistsByFilter(filter)
            // TODO dedicated mapper
            .map {
                ArtistOutput(
                    id = it.id,
                    name = it.name,
                    albums =
                        it.albums.map { albumDto ->
                            AlbumOutput(
                                id = albumDto.id,
                                title = albumDto.title,
                            )
                        },
                )
            }
    }
}
