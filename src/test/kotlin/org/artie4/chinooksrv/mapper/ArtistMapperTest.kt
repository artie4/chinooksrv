package org.artie4.chinooksrv.mapper

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.artie4.chinooksrv.config.MapperTestConfig
import org.artie4.chinooksrv.stub.AlbumStub
import org.artie4.chinooksrv.stub.ArtistStub
import org.artie4.chinooksrv.stub.TrackStub
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [MapperTestConfig::class])
class ArtistMapperTest {
    @Autowired
    private lateinit var underTest: ArtistMapper

    @Test
    fun `should map to ArtistDto successfully`() {
        val track1 = TrackStub.getTrackEntity(id = 1, name = "track1")
        val track2 = TrackStub.getTrackEntity(id = 2, name = "track2")
        val album1 = AlbumStub.getAlbumEntity(id = 1, title = "album1", tracks = mutableSetOf(track1))
        val album2 = AlbumStub.getAlbumEntity(id = 2, title = "album2", tracks = mutableSetOf(track2))
        val artist = ArtistStub.getArtistEntity(albums = mutableSetOf(album1, album2))

        val result = underTest.toArtistDto(artist)

        assertSoftly {
            result.id shouldBe artist.id
            result.name shouldBe artist.name
            result.albums.size shouldBe artist.albums.size
            result.albums.first { it.title == album1.title }.tracks.first().name shouldBe track1.name
            result.albums.first { it.title == album2.title }.tracks.first().name shouldBe track2.name
        }
    }

    @Test
    fun `should map to ArtistOutput successfully`() {
        val track1 = TrackStub.getTrackDto(name = "track1")
        val track2 = TrackStub.getTrackDto(name = "track2")
        val album1 = AlbumStub.getAlbumDto(title = "album1", tracks = mutableSetOf(track1))
        val album2 = AlbumStub.getAlbumDto(title = "album2", tracks = mutableSetOf(track2))
        val artist = ArtistStub.getArtistDto(albums = mutableSetOf(album1, album2))

        val result = underTest.toArtistOutput(artist)

        assertSoftly {
            result.id shouldBe artist.id
            result.name shouldBe artist.name
            result.albums.size shouldBe artist.albums.size
            result.albums.first { it.title == album1.title }.tracks.first().name shouldBe track1.name
            result.albums.first { it.title == album2.title }.tracks.first().name shouldBe track2.name
        }
    }
}
