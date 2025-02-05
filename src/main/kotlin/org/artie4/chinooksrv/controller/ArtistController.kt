package org.artie4.chinooksrv.controller

import org.artie4.chinooksrv.dto.ArtistDto
import org.artie4.chinooksrv.service.ArtistService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("api/v1")
class ArtistController(
    private val artistService: ArtistService,
) {
    @GetMapping("artist/{id}")
    fun getArtistById(
        @PathVariable id: Long,
    ): ArtistDto {
        return artistService.getArtistById(id)
    }

    @GetMapping("artists/by_name")
    fun getArtistByName(
        @RequestParam name: String,
    ): List<ArtistDto> {
        return artistService.getArtistByName(name)
    }

    @GetMapping("artists/by_title")
    fun getArtistByAlbumTitle(
        @RequestParam("album_title") albumTitle: String,
    ): List<ArtistDto> {
        return artistService.getArtistByAlbumTitle(albumTitle)
    }
}
