package org.artie4.chinooksrv.service.impl

import org.artie4.chinooksrv.dto.ArtistDto
import org.artie4.chinooksrv.mapper.ArtistMapper
import org.artie4.chinooksrv.repository.ArtistRepository
import org.artie4.chinooksrv.service.ArtistService
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
}
