package org.artie4.chinooksrv.service.impl

import com.netflix.dgs.codegen.generated.types.ArtistSearchFilter
import jakarta.persistence.criteria.Join
import jakarta.persistence.criteria.JoinType
import org.artie4.chinooksrv.dto.ArtistDto
import org.artie4.chinooksrv.entity.Album
import org.artie4.chinooksrv.entity.Artist
import org.artie4.chinooksrv.mapper.ArtistMapper
import org.artie4.chinooksrv.repository.ArtistRepository
import org.artie4.chinooksrv.service.ArtistService
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class ArtistServiceImpl(
    private val artistRepository: ArtistRepository,
    private val artistMapper: ArtistMapper,
) : ArtistService {
    override fun getArtistById(id: Long): ArtistDto {
        val artist =
            artistRepository.findByIdWithAlbumsAndTracks(id)
                ?: throw IllegalArgumentException("Artist not found with id: $id")

        return artistMapper.toArtistDto(artist)
    }

    override fun getArtistByName(name: String): List<ArtistDto> =
        artistRepository.findByNameWithAlbumsAndTracks(name)
            .map { artistMapper.toArtistDto(it) }

    override fun getArtistByAlbumTitle(albumTitle: String): List<ArtistDto> =
        artistRepository.findByAlbumTitleWithAlbumsAndTracks(albumTitle)
            .map { artistMapper.toArtistDto(it) }

    override fun findArtistsByFilter(filter: ArtistSearchFilter): List<ArtistDto> {
        var spec = alwaysTrue()
        filter.artistId?.also {
            spec = spec.and(searchById(it))
        }

        filter.artistName?.also {
            spec = spec.and(searchByName(it))
        }

        filter.albumTitle?.also {
            spec = spec.and(searchByAlbumTitle(it))
        }

        return artistRepository.findAll(spec)
            .map { artistMapper.toArtistDto(it) }
    }

    private fun searchById(id: Long): Specification<Artist> = Specification { root, _, cb -> cb.equal(root.get<Long>("id"), id) }

    private fun searchByName(name: String): Specification<Artist> =
        Specification { root, _, cb ->
            cb.like(cb.lower(root.get("name")), "%${name.lowercase()}%")
        }

    private fun searchByAlbumTitle(title: String): Specification<Artist> =
        Specification { root, _, cb ->
            val albumsJoin: Join<Artist, Album> = root.join("albums", JoinType.INNER)
            cb.like(cb.lower(albumsJoin.get("title")), "%${title.lowercase()}%")
        }

    private fun alwaysTrue(): Specification<Artist> =
        Specification {
                _, _, cb ->
            cb.conjunction()
        }
}
