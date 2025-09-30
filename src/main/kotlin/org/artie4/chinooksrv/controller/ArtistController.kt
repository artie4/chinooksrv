package org.artie4.chinooksrv.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.artie4.chinooksrv.dto.ArtistDto
import org.artie4.chinooksrv.service.ArtistService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1")
class ArtistController(
    private val artistService: ArtistService,
) {
    @Operation(
        summary = "Artist by id",
        security = [SecurityRequirement(name = "JWT")],
    )
    @GetMapping("artist/{id}")
    fun getArtistById(
        @PathVariable id: Long,
    ): ArtistDto {
        return artistService.getArtistById(id)
    }

    @Operation(
        summary = "Artists by name",
        security = [SecurityRequirement(name = "JWT")],
    )
    @GetMapping("artists/by_name")
    fun getArtistByName(
        @RequestParam name: String,
    ): List<ArtistDto> {
        return artistService.getArtistByName(name)
    }

    @Operation(
        summary = "Artists by album name",
        security = [SecurityRequirement(name = "JWT")],
    )
    @GetMapping("artists/by_title")
    fun getArtistByAlbumTitle(
        @RequestParam("album_title") albumTitle: String,
    ): List<ArtistDto> {
        return artistService.getArtistByAlbumTitle(albumTitle)
    }
}
