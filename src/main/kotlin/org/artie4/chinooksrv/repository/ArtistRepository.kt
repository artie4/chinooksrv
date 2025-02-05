package org.artie4.chinooksrv.repository

import org.artie4.chinooksrv.entity.Artist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ArtistRepository : JpaRepository<Artist, Long> {
    @Query("select a from Artist a left join fetch a.albums alb left join fetch alb.tracks where a.id = :id")
    fun findByIdWithAlbumsAndTracks(
        @Param("id") id: Long,
    ): Artist?

    @Query(
        "select a from Artist a left join fetch a.albums alb left join fetch alb.tracks " +
            "where lower(a.name) like lower(concat(:name, '%'))",
    )
    fun findByNameWithAlbumsAndTracks(
        @Param("name") name: String,
    ): List<Artist>

    @Query(
        "select a from Artist a left join fetch a.albums alb left join fetch alb.tracks " +
            "where lower(alb.title) like lower(concat(:title, '%'))",
    )
    fun findByAlbumTitleWithAlbumsAndTracks(
        @Param("title") title: String,
    ): List<Artist>
}
